package com.benzhz.qcfive.calculator.impl;

import com.benzhz.qcfive.calculator.ISPCCpkCalculator;
import com.benzhz.qcfive.calculator.bo.SPCCpkBo;
import com.benzhz.qcfive.calculator.bo.SPCCpkQuotaBo;
import com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.utils.CalculateUtil;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.benzhz.qcfive.common.constants.CommonConstants.pattern;
import static com.benzhz.qcfive.utils.AssertUtil.isMatch;
import static com.benzhz.qcfive.utils.CalculateUtil.cdf;

/**
 * cpk计算
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.impl
 * @Project：qc-five
 * @name：SPCCpkCalculator
 * @Date：2025/2/16 22:15
 * @Filename：SPCCpkCalculator
 */
@Service
public class SPCCpkCalculator implements ISPCCpkCalculator {


    public static final String PU = "PU";
    public static final String PL = "PL";
    public static final String PK = "PK";
    public static final String STDDEV = "STDDEV";
    public static final String PPM_LSL_LT = "PPM<LSL";
    public static final String PPM_LSL_GT = "PPM>USL";
    public static final String PPM_TOTAL = "PPM TOTAL";

    //柱状图间隔
    public static double BAR_SIZE = 10;

    /**
     * cpk计算
     *
     * @param
     * @param chartInfo
     * @return
     */
    @Override
    public SPCCpkBo calculate(List<Map<String, String>> sampleDataList, SpcChart chartInfo) {
        return calculateStatistics(sampleDataList, chartInfo);
    }

    public static SPCCpkBo calculateStatistics(List<Map<String, String>> sampleDataList, SpcChart chartInfo) {
        // 用于匹配 sample + 数字的正则表达式

        int totalCount = 0;
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        //样本数值
        List<List<Double>> sampleValuesGroup = new ArrayList<>();
        List<Double> sampleValuesAll = new ArrayList<>();
        // 遍历每个 Map
        for (Map<String, String> map : sampleDataList) {
            List<Double> groupValue = new ArrayList<>();
            // 遍历 Map 中的每个键值对
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                // 检查键是否满足 sample + 数字的格式
                if (isMatch(key, pattern)) {
                    try {
                        // 将值转换为 double 类型
                        double value = Double.parseDouble(entry.getValue());
                        totalCount++;
                        sum += value;
                        if (value > max) {
                            max = value;
                        }
                        if (value < min) {
                            min = value;
                        }
                        groupValue.add(value);
                        sampleValuesAll.add(value);
                    } catch (NumberFormatException e) {
                        // 处理值无法转换为数字的情况
                        System.err.println("无法将值 " + entry.getValue() + " 转换为数字，忽略该样本。");
                    }
                }
            }
            sampleValuesGroup.add(groupValue);
        }
        //数值排序
        Collections.sort(sampleValuesAll);
        //子组容量
        int subgroupSize = sampleValuesGroup.get(0).size();
        // 计算平均值
        double average = totalCount > 0 ? sum / totalCount : 0;
        //上规格
        double usl = chartInfo.getStandard() + chartInfo.getScope();
        //下规格
        double lsl = chartInfo.getStandard() - chartInfo.getScope();
        //计算cpk，ppk，ppm等
        List<SPCCpkQuotaBo> quotas = buildCpkQuota(sampleValuesGroup, usl, lsl);
        //柱状图计算
        Map<Double, Long> barMap = buildBarMap(sampleValuesAll, usl, lsl);
        CalculateUtil.getStandardDeviation(sampleValuesAll);
        //整体正态分布曲线计算
        double stdDev = CalculateUtil.getStandardDeviation(sampleValuesAll);
        double[] normalDistribution = calculateNormalDistribution(barMap.keySet(), sampleValuesAll, stdDev);
        //组内正态分布曲线计算
        double[] withinNormalDistribution = new double[sampleValuesAll.size()];
        //单值算不出组内标准差的
        if (subgroupSize > 1) {
            double withInStdDev = calculateWithinStdDev(sampleValuesGroup);
            withinNormalDistribution = calculateNormalDistribution(barMap.keySet(), sampleValuesAll, withInStdDev);
        }
        return new SPCCpkBo(totalCount, average, stdDev, normalDistribution,
                withinNormalDistribution, barMap, max, min, chartInfo.getSubgroupSize(),
                chartInfo.getChart(), chartInfo.getStandard(), usl, lsl, stdDev * 3, -stdDev * 3, quotas);
    }

    /**
     * 正态分布值
     *
     * @param xAxis  x轴坐标
     * @param data   样本数据
     * @param stdDev 标准差
     * @return
     */
    public static double[] calculateNormalDistribution(Set<Double> xAxis, List<Double> data, double stdDev) {
        int numPoints = xAxis.size();
        // 创建描述性统计对象
        DescriptiveStatistics stats = new DescriptiveStatistics();
        data.forEach(stats::addValue);
        // 计算均值和标准差
        double mean = stats.getMean();
        // 创建正态分布对象
        NormalDistribution normalDistribution = new NormalDistribution(mean, stdDev);
        // 计算正态分布曲线上的点
        double[] curvePoints = new double[numPoints];
        double min = xAxis.stream().min(Double::compare).orElse(0.0);
        double max = xAxis.stream().max(Double::compare).orElse(0.0);
        double step = (max - min) / (numPoints - 1);

        for (int i = 0; i < numPoints; i++) {
            double x = min + i * step;
            curvePoints[i] = normalDistribution.density(x);
        }

        return curvePoints;
    }

    /**
     * 计算组内标准差
     *
     * @param data 输入的数据，每个子列表代表一个组
     * @return 组内标准差
     */
    public static double calculateWithinStdDev(List<List<Double>> data) {
        int subGroupSize = data.get(0).size();
        double sumOfRange = 0.0;
        // 遍历每个组
        for (List<Double> group : data) {
            if (!group.isEmpty()) {
                double groupRange = calculateRange(group);
                sumOfRange += groupRange;
            }
        }

        double averageRange = sumOfRange / data.size();
        double d2 = getD2(subGroupSize);
        return averageRange / d2;
    }


    /**
     * 构建柱状图数据
     *
     * @param data
     * @param usl
     * @param lsl
     * @return <刻度范围，范围内值数量>
     */
    public static Map<Double, Long> buildBarMap(List<Double> data, double usl, double lsl) {

        if (data == null || data.isEmpty()) {
            return new TreeMap<>();
        }


        Map<Double, Long> rangeCountMap = new TreeMap<>();
        // 找到最小值
        double min = CalculateUtil.getMin(data);
        // 找到最大值
        double max = CalculateUtil.getMax(data);

        double RANGE = ((max - min) / BAR_SIZE);
        RANGE = BigDecimal.valueOf(RANGE).setScale(4, RoundingMode.HALF_UP).doubleValue();
        double RANGE_OPPOSITE = 1 / RANGE;
        //每range数值为一个柱状图区间，值默认为0
        double pre = 0l;
        for (double i = 0; pre <= usl; i++) {
            double v = (lsl + i * RANGE);
            v = BigDecimal.valueOf(v).setScale(4, RoundingMode.HALF_UP).doubleValue();
            rangeCountMap.put(v, 0L);
            pre = v;
        }
        int index = 0;
        // 以 RANGE 为间隔统计每个区间的数量
        for (double value : data) {
            List<Double> rangeKey = new ArrayList<>(rangeCountMap.keySet());
            for (int i = index ; i < rangeKey.size(); i++) {
                if (i + 1 == rangeKey.size() || (value >= rangeKey.get(i) && value <= rangeKey.get(i + 1))) {
                    Double currentKey = rangeKey.get(i);
                    Long count = rangeCountMap.getOrDefault(currentKey, 0L);
                    rangeCountMap.put(currentKey, ++count);
                    index = i;
                    break;
                }
            }
        }

        return rangeCountMap;
    }

    /**
     * 计算cpk，ppk，ppm等
     *
     * @param sampleValuesGroup
     * @param usl
     * @param lsl
     * @return
     */
    private static List<SPCCpkQuotaBo> buildCpkQuota(List<List<Double>> sampleValuesGroup, double usl, double lsl) {
        List<SPCCpkQuotaBo> result = new ArrayList<>();
        //子组容量
        int subgroupSize = sampleValuesGroup.get(0).size();
        //cpk计算
        Map<String, Double> overallMap = calculateCpk(sampleValuesGroup, usl, lsl);
        //PPM计算
        overallMap.putAll(calculateOverallPPM(sampleValuesGroup, usl, lsl));
        Map<String, Double> withinMap = new LinkedHashMap<>();
        //单值算不出组内标准差的
        if (subgroupSize > 1) {
            //组内cpk与ppm
            withinMap = calculatePpk(sampleValuesGroup, usl, lsl);
            withinMap.putAll(calculateWithinGroupPPM(sampleValuesGroup, usl, lsl));
        }
        for (String key : overallMap.keySet()) {
            Double gobleValue = overallMap.get(key);
            Double groupValue = withinMap.get(key);
            result.add(new SPCCpkQuotaBo(key, gobleValue, groupValue));
        }
        return result;
    }


    // 计算整体 CPK 的方法
    public static LinkedHashMap<String, Double> calculateCpk(List<List<Double>> sampleValuesGroup, double usl, double lsl) {
        List<Double> allValues = new ArrayList<>();
        for (List<Double> subgroup : sampleValuesGroup) {
            allValues.addAll(subgroup);
        }
        double mean = calculateMean(allValues);
        double stdDev = calculateStdDev(allValues, mean);
        double cpu = (usl - mean) / (3 * stdDev);
        double cpl = (mean - lsl) / (3 * stdDev);
        return new LinkedHashMap<String, Double>() {
            {
                put(STDDEV, stdDev);
                put(PU, cpu);
                put(PL, cpl);
                put(PK, Math.min(cpu, cpl));

            }
        };
    }

    // 计算组内 CPK 的方法
    public static LinkedHashMap<String, Double> calculatePpk(List<List<Double>> sampleValuesGroup, double usl, double lsl) {

        //标准差
        double withinStdDev = calculateWithinStdDev(sampleValuesGroup);

        //平均值
        double overallMean = calculateGroupMean(sampleValuesGroup);

        double ppu = (usl - overallMean) / (3 * withinStdDev);
        double ppl = (overallMean - lsl) / (3 * withinStdDev);
        return new LinkedHashMap<String, Double>() {
            {
                put(STDDEV, withinStdDev);
                put(PU, ppu);
                put(PL, ppl);
                put(PK, Math.min(ppu, ppl));

            }
        };
    }


    // 计算整体的 PPM 指标
    public static LinkedHashMap<String, Double> calculateOverallPPM(List<List<Double>> sampleValuesGroup, double LSL, double USL) {
        List<Double> allValues = new ArrayList<>();
        for (List<Double> subgroup : sampleValuesGroup) {
            allValues.addAll(subgroup);
        }
        double mean = calculateMean(allValues);
        double stdDev = calculateStdDev(allValues, mean);
        return calculatePPM(mean, stdDev, LSL, USL);
    }

    // 计算组内的 PPM 指标
    public static LinkedHashMap<String, Double> calculateWithinGroupPPM(List<List<Double>> sampleValuesGroup, double LSL, double USL) {
        double totalMean = 0;
        int totalCount = 0;
        for (List<Double> subgroup : sampleValuesGroup) {
            double subgroupMean = calculateMean(subgroup);
            totalMean += subgroupMean * subgroup.size();
            totalCount += subgroup.size();
        }
        totalMean /= totalCount;
        double withInStdDev = calculateWithinStdDev(sampleValuesGroup);
        return calculatePPM(totalMean, withInStdDev, LSL, USL);
    }

    // 计算 PPM 结果
    private static LinkedHashMap<String, Double> calculatePPM(double mean, double stdDev, double LSL, double USL) {
        double zLSL = (mean - LSL) / stdDev;
        double zUSL = (USL - mean) / stdDev;
        double ppmBelowLSL = cdf(zLSL) * 1e6;
        double ppmAboveUSL = cdf(zUSL) * 1e6;
        double ppmTotal = ppmBelowLSL + ppmAboveUSL;
        return new LinkedHashMap<String, Double>() {
            {
                put(PPM_LSL_LT, ppmBelowLSL);
                put(PPM_LSL_GT, ppmAboveUSL);
                put(PPM_TOTAL, ppmTotal);
            }
        };
    }


    // 计算 d2 常数的方法，这里简单实现一个常用样本量对应的 d2 值
    private static double getD2(int subgroupSize) {
        return SpcChartTableConstantsPool.getConst("d2", subgroupSize);
    }

    // 计算均值的方法
    private static double calculateMean(List<Double> values) {
        return CalculateUtil.getMean(values.stream().mapToDouble(Double::doubleValue).toArray());
    }

    // 计算均值的方法
    private static double calculateGroupMean(List<List<Double>> values) {
        return values.stream().flatMap(List::stream).mapToDouble(Double::doubleValue).average().orElse(0);
    }

    // 计算标准差的方法
    private static double calculateStdDev(List<Double> values, double mean) {
        return CalculateUtil.getStandardDeviation(values);
    }

    // 计算极差的方法
    private static double calculateRange(List<Double> values) {
        return CalculateUtil.getRange(values.stream().mapToDouble(Double::doubleValue).toArray());
    }


}
