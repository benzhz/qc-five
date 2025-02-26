package com.benzhz.qcfive.calculator.impl;

import com.benzhz.qcfive.calculator.INormalDistributionTestCalculator;
import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;
import com.benzhz.qcfive.calculator.bo.NormalDistributionTestResultBo;
import com.benzhz.qcfive.calculator.bo.variable.NormalProbabilityTestBo;
import com.benzhz.qcfive.calculator.tester.ITester;
import com.benzhz.qcfive.common.exception.ServiceException;
import com.benzhz.qcfive.utils.CalculateUtil;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

import static com.benzhz.qcfive.common.constants.CommonConstants.SAMPLE_D;
import static com.benzhz.qcfive.common.exception.enums.GlobalErrorCodeConstants.VALUE_CONVERT_ERROR;

/**
 * 正态性检验
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.impl
 * @Project：qc-five
 * @name：NormalDistributionTestCalculator
 * @Date：2025/2/16 22:15
 * @Filename：NormalDistributionTestCalculator
 */
@Service
public class NormalDistributionTestCalculator implements INormalDistributionTestCalculator {

    @Autowired
    private List<ITester> tests;

    @Override
    public NormalDistributionTestResultBo calculate(List<Map<String, String>> sampleDataList) {
        Pattern pattern = Pattern.compile(SAMPLE_D);
        //样本数值
        List<Double> dataList = new ArrayList<>();
        List<NormalDistributionTestBo> result = new ArrayList<>();
        // 遍历每个 Map
        for (Map<String, String> map : sampleDataList) {
            // 遍历 Map 中的每个键值对
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                // 检查键是否满足 sample + 数字的格式
                if (pattern.matcher(key).matches()) {
                    try {
                        // 将值转换为 double 类型
                        double value = Double.parseDouble(entry.getValue());
                        dataList.add(value);
                    } catch (NumberFormatException e) {
                        // 处理值无法转换为数字的情况
                        throw new ServiceException(VALUE_CONVERT_ERROR, entry.getValue());
                    }
                }
            }
        }
        // 对样本数据进行排序
        Collections.sort(dataList);
        // 将 List<Double> 转换为 double[] 数组
        double[] data = CalculateUtil.doubleListToArray(dataList);
        for (ITester tester : tests) {
            result.add(tester.test(data));
        }
        NormalProbabilityTestBo normalProbabilityTestBo = calculateNormalProbabilityData(dataList);
        return new NormalDistributionTestResultBo(normalProbabilityTestBo, result);
    }

    // 计算正态概率图的数据
    public static NormalProbabilityTestBo calculateNormalProbabilityData(List<Double> sampleData) {


        double mean = CalculateUtil.getMean(sampleData);
        double stdDev = CalculateUtil.getStandardDeviation(sampleData);
        // 创建正态分布对象（均值0，标准差1）
        NormalDistribution normalDist = new NormalDistribution(mean, stdDev);

        // 样本数量
        int n = sampleData.size();

        // 存储理论值和 CDF 值
        List<Double> theoreticalValues = new ArrayList<>();
        List<Double> cdfValues = new ArrayList<>();

        // 遍历样本数据，计算每个点对应的理论值和 CDF 值
        for (int i = 0; i < n; i++) {

            // 计算分位数的概率值 p = (i - 0.5) / n
            double p = (i + 0.5) / n;

            // 使用正态分布的反函数（inverse CDF）计算理论值
            double theoreticalValue = normalDist.inverseCumulativeProbability(p);

            // 添加到列表中
            theoreticalValues.add(theoreticalValue);
            // 保存 CDF 值
            cdfValues.add(p);
        }
        // 进行线性回归
//        SimpleRegression regression = new SimpleRegression();
//        for (int i = 0; i < n; i++) {
//            regression.addData(theoreticalValues.get(i), cdfValues.get(i) * 100);
//        }
//        double slope = regression.getSlope();
//        double intercept = regression.getIntercept();

        // 返回封装的对象
        return new NormalProbabilityTestBo(stdDev, mean, theoreticalValues, cdfValues, sampleData);
    }
}
