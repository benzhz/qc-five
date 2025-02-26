package com.benzhz.qcfive.calculator.bo;

import com.benzhz.qcfive.utils.Mutil;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.benzhz.qcfive.common.constants.CommonConstants.SYSTEM_COMMON_PRECISION;

/**
 * cpk所需参数
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.vo
 * @Project：qc-five
 * @name：SPCCpkVo
 * @Date：2025/2/16 22:15
 * @Filename：SPCCpkVo
 */

@Data
public class SPCCpkBo {
    //总样本数
    private int totalCount;
    //平均值
    private double average;
    //标准差
    private double stdDev;
    //整体正态分布
    private double[] normalDistribution;
    //组内正态分布
    private double[] withinNormalDistribution;
    //柱状图数据
    private Map<Double, Long> barMap;
    //最大值
    private double max;
    //最小值
    private double min;
    //子组大小
    private int subgroupSize;
    //图表类型
    private String chart;
    //目标值
    private Double standard;
    //上规格
    private Double usl;
    //下规格
    private Double lsl;
    //+3Sigma
    private double positive3Sigma;
    //-3Sigma
    private double negative3Sigma;
    //cpk指标
    private List<SPCCpkQuotaBo> quotas;

    public SPCCpkBo(int totalCount, double average, double stdDev, double[] normalDistribution, double[] withinNormalDistribution, Map<Double, Long> barMap, double max, double min, int subgroupSize, String chart, Double standard, Double usl, Double lsl, double positive3Sigma, double negative3Sigma, List<SPCCpkQuotaBo> quotas) {
        this.totalCount = totalCount;
        this.average = Mutil.round(average, SYSTEM_COMMON_PRECISION);;
        this.stdDev = Mutil.round(stdDev, SYSTEM_COMMON_PRECISION);
        this.normalDistribution = normalDistribution;
        this.withinNormalDistribution = withinNormalDistribution;
        this.barMap = barMap;
        this.max = max;
        this.min = min;
        this.subgroupSize = subgroupSize;
        this.chart = chart;
        this.standard = standard;
        this.usl = usl;
        this.lsl = lsl;
        this.positive3Sigma = Mutil.round(positive3Sigma, SYSTEM_COMMON_PRECISION);
        this.negative3Sigma = Mutil.round(negative3Sigma, SYSTEM_COMMON_PRECISION);
        this.quotas = quotas;
    }
}
