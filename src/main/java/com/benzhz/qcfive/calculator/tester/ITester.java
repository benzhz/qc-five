package com.benzhz.qcfive.calculator.tester;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.test
 * @Project：qc-five
 * @name：ITest
 * @Date：2025/2/16 22:15
 * @Filename：ITest
 */
public interface ITester {
    String JUDGE_SUIT = "在显著性水平 %s 下，数据%s服从正态分布。";
    double alpha = 0.05;
    String NO_CN = "不";
    NormalDistributionTestBo test(double[] data);
}
