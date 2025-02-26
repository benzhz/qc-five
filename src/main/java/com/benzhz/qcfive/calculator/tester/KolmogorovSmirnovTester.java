package com.benzhz.qcfive.calculator.tester;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;
import jdistlib.disttest.NormalityTest;
import org.springframework.stereotype.Component;

/**
 * 柯尔莫哥洛夫 - 斯米尔诺夫检验（K - S 检验）
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.test
 * @Project：qc-five
 * @name：KolmogorovSmirnovTest
 * @Date：2025/2/16 22:15
 * @Filename：KolmogorovSmirnovTest
 */
@Component
public class KolmogorovSmirnovTester implements ITester {
    @Override
    public NormalDistributionTestBo test(double[] data) {
        double statistic = NormalityTest.kolmogorov_lilliefors_statistic(data);
        double pValue = NormalityTest.kolmogorov_lilliefors_pvalue(statistic,data.length);
        String judge = String.format(JUDGE_SUIT, alpha, pValue < alpha ? NO_CN : "");
        return new NormalDistributionTestBo("Kolmogorov Smirnov校验",statistic,pValue,judge);
    }
}
