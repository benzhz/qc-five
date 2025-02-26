package com.benzhz.qcfive.utils;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.util.List;

/**
 * 科学计算工具
 * @Author：zhz
 * @Package：com.benzhz.qcfive.utils
 * @Project：qc-five
 * @name：CalculateUtil
 * @Date：2025/2/16 22:15
 * @Filename：CalculateUtil
 */
public class CalculateUtil {
    /**
     * 求平均值
     *
     * @param values
     * @return
     * @throws MathIllegalArgumentException
     */
    public static double getMean(double[] values) throws MathIllegalArgumentException {
        return StatUtils.mean(values) ;
    }



    /**
     * 求最小值
     *
     * @param values
     * @return
     * @throws MathIllegalArgumentException
     */
    public static double getMin(List<Double> values) throws MathIllegalArgumentException {
        return StatUtils.min(values.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
    }

    /**
     * 求最大值
     *
     * @param values
     * @return
     * @throws MathIllegalArgumentException
     */
    public static double getMax(List<Double> values) throws MathIllegalArgumentException {
        return StatUtils.max(values.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
    }

    /**
     * 求平均值
     *
     * @param values
     * @return
     * @throws MathIllegalArgumentException
     */
    public static double getMean(List<Double> values) throws MathIllegalArgumentException {
        return getMean(values.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
    }

    /**
     * 标准差
     *
     * @param values
     * @return
     */
    public static double getStandardDeviation(double[] values) {
        return new StandardDeviation().evaluate(values);
    }

    /**
     * 标准差
     *
     * @param values
     * @return
     */
    public static double getStandardDeviation(List<Double> values) {
        return getStandardDeviation(values.stream()
                .mapToDouble(Double::doubleValue)
                .toArray());
    }

    /**
     * 计算 Z 值对应的累积分布函数值
     */
    public static double cdf(double z) {
        NormalDistribution normal = new NormalDistribution(0, 1);
        return normal.cumulativeProbability(z);
    }

    /**
     * 极差
     *
     * @param values
     * @return
     */
    public static double getRange(double[] values) {
        if (values == null) {
            throw new java.lang.NumberFormatException();
        }
        double max = StatUtils.max(values);
        double min = StatUtils.min(values);
        return Mutil.subtract(max, min);
    }

    public static double[] getMovingRange(double[] data) {
        double[] movingRange = new double[data.length - 1];

        for (int i = 0; i < data.length - 1; i++) {
            movingRange[i] = Math.abs(Mutil.subtract(data[i + 1], data[i])); // 计算移动极差
        }

        return movingRange;
    }


    /**
     * 求中位数
     *
     * @param values
     * @return
     */
    public static double getMedian(double[] values) {
        return new Median().evaluate(values);
    }

    /**
     * 求过程均值
     *
     * @param values
     * @return
     */
    public static double getProcessMean(List<List<Double>> values) {
        double[] subResults = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            subResults[i] = getMean(doubleListToArray(values.get(i)));
        }
        return getMean(subResults);
    }

    /**
     * 求过程平均极差
     *
     * @param values
     * @return
     */
    public static double getProcessRangeMean(List<List<Double>> values) {
        double[] subResults = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            subResults[i] = getRange(doubleListToArray(values.get(i)));
        }
        return getMean(subResults);
    }

    /**
     * 求过程平均标准差
     *
     * @param values
     * @return
     */
    public static double getProcessStandardDeviationMean(List<List<Double>> values) {
        double[] subResults = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            subResults[i] = getStandardDeviation(doubleListToArray(values.get(i)));
        }
        return getMean(subResults);
    }

    /**
     * 求中位数均值
     *
     * @param values
     * @return
     */
    public static double getProcessMedianMean(List<List<Double>> values) {
        double[] subResults = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            subResults[i] = getMedian(doubleListToArray(values.get(i)));
        }
        return getMean(subResults);
    }

    public static double[] doubleListToArray(List<Double> list) {
        double[] result = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
