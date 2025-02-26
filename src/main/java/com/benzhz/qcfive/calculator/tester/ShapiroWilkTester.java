package com.benzhz.qcfive.calculator.tester;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;
import jdistlib.disttest.NormalityTest;
import org.springframework.stereotype.Component;

/**
 * 夏皮罗 - 威尔克检验
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.test
 * @Project：qc-five
 * @name：ShapiroWilkTest
 * @Date：2025/2/16 22:15
 * @Filename：ShapiroWilkTest
 */
@Component
public class ShapiroWilkTester implements ITester {

    @Override
    public NormalDistributionTestBo test(double[] data) {
        double statistic = NormalityTest.shapiro_wilk_statistic(data);
        double pValue = NormalityTest.shapiro_wilk_pvalue(statistic, data.length);
        String judge = String.format(JUDGE_SUIT, alpha, pValue < alpha ? NO_CN : "");
        return new NormalDistributionTestBo("Shapiro - Wilk 检验", statistic, pValue, judge);
    }


}
