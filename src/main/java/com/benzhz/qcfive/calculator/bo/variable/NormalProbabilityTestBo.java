package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.utils.Mutil;
import lombok.Data;

import java.util.List;

import static com.benzhz.qcfive.common.constants.CommonConstants.SYSTEM_COMMON_PRECISION;

/**
 * 正态性检验结果
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.variable
 * @Project：qc-five
 * @name：NormalProbabilityTestBo
 * @Date：2025/2/16 22:15
 * @Filename：NormalProbabilityTestBo
 */

@Data
public class NormalProbabilityTestBo {
    //斜率
    private double slope;
    //截距
    private double intercept;
    //理论值
    private List<Double> theoreticalValues;
    //cdf值
    private List<Double> cdfValues;
    //实际值
    private List<Double> sampleData;

    public NormalProbabilityTestBo(double slope, double intercept, List<Double> theoreticalValues, List<Double> cdfValues, List<Double> sampleData) {
        this.slope = Mutil.round(slope, SYSTEM_COMMON_PRECISION);
        this.intercept = Mutil.round(intercept, SYSTEM_COMMON_PRECISION);
        this.theoreticalValues = theoreticalValues;
        this.cdfValues = cdfValues;
        this.sampleData = sampleData;
    }
}
