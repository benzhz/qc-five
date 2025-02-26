package com.benzhz.qcfive.calculator.tester;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;
import jdistlib.disttest.NormalityTest;
import org.springframework.stereotype.Component;


/**
 * 安德森 - 达林检验
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.test
 * @Project：qc-five
 * @name：AndersonDarlingTest
 * @Date：2025/2/16 22:15
 * @Filename：AndersonDarlingTest
 */
@Component
public class AndersonDarlingTester implements ITester {


    @Override
    public NormalDistributionTestBo test(double[] data) {
        double statistic = NormalityTest.anderson_darling_statistic(data);
        double pValue = NormalityTest.anderson_darling_pvalue(statistic, data.length);
        String judge = String.format(JUDGE_SUIT, alpha, pValue < alpha ? NO_CN : "");
        return new NormalDistributionTestBo("Anderson Darling校验", statistic, pValue, judge);
    }
}
