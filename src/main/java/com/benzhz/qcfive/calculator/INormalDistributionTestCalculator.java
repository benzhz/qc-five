package com.benzhz.qcfive.calculator;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestResultBo;

import java.util.List;
import java.util.Map;

/**
 * 正态性检验
 * @Author：zhz
 * @Package：com.benzhz.qcfive.caculation
 * @Project：qc-five
 * @name：AbStractSPCChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：AbStractSPCChartCalculator
 */
public  interface INormalDistributionTestCalculator {


    NormalDistributionTestResultBo calculate(List<Map<String, String>> sampleDataList);
}
