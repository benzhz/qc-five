package com.benzhz.qcfive.calculator;

import com.benzhz.qcfive.calculator.bo.SPCCpkBo;
import com.benzhz.qcfive.entity.SpcChart;

import java.util.List;
import java.util.Map;

/**
 * cpk计算
 * @Author：zhz
 * @Package：com.benzhz.qcfive.caculation
 * @Project：qc-five
 * @name：AbStractSPCChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：AbStractSPCChartCalculator
 */
public  interface ISPCCpkCalculator {


    SPCCpkBo calculate(List<Map<String, String>> sampleDataList, SpcChart chartInfo);
}
