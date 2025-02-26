package com.benzhz.qcfive.calculator;

import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;

import java.util.List;
import java.util.Map;

/**
 * spc控制图计算
 * @Author：zhz
 * @Package：com.benzhz.qcfive.caculation
 * @Project：qc-five
 * @name：AbStractSPCChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：AbStractSPCChartCalculator
 */
public  interface ISPCChartCalculator {

    public SPCControlCharBo calculate(String chart, List<Map<String, String>> sampleData);

}
