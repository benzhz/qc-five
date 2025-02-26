package com.benzhz.qcfive.calculator.impl.variable;


import com.benzhz.qcfive.calculator.ISPCChartCalculator;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.benzhz.qcfive.common.constants.CommonConstants.pattern;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.*;
import static com.benzhz.qcfive.utils.AssertUtil.isMatch;

/**
 * 计量型控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：AbstractVariableSPCChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：AbstractVariableSPCChartCalculator
 */
public abstract class AbstractVariableCalculator implements ISPCChartCalculator {


    /**
     * 数据适配不同控制图的入参
     *
     * @param chart
     * @param sampleData
     * @return
     */
    public SPCControlCharBo calculate(String chart, List<Map<String, String>> sampleData) {
        List<VariableBo> data = new ArrayList<>();
        for (Map<String, String> sampleMap : sampleData) {
            VariableBo sampleVariableData = new VariableBo();
            String sampleTime = sampleMap.get(SAMPLE_TIME);
            //获取样本时间
            sampleVariableData.setSampleTime(sampleTime);
            //单值控制图
            if (CHART_XMR.equals(chart)) {
                //样本数量不固定，样本字段名是sample+数字，单值是sample1
                String sample = sampleMap.get(SAMPLE_PREFIX + 1);
                sampleVariableData.setSample(Double.parseDouble(sample));
            } else {
                //其他多值样本控制图
                List<Double> samples = sampleMap.keySet().stream().filter(s -> isMatch(s, pattern)
                        )
                        .map(sampleKey -> Double.parseDouble(sampleMap.get(sampleKey)))
                        .collect(Collectors.toList());
                sampleVariableData.setSamples(samples);
            }
            data.add(sampleVariableData);
        }
        //执行spc参数计算
        return this.calculate(data);
    }


    /**
     * 控制图计算
     *
     * @param
     * @return
     */
    public abstract SPCControlCharBo calculate(List<VariableBo> data);

}
