package com.benzhz.qcfive.calculator.tester;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestBo;
import jdistlib.disttest.NormalityTest;
import org.springframework.stereotype.Component;

/**
 * 皮尔逊综合正态性检验
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.test
 * @Project：qc-five
 * @name：PearsonOmnibusNormalityTest
 * @Date：2025/2/16 22:15
 * @Filename：PearsonOmnibusNormalityTest
 */
@Component
public class PearsonOmnibusNormalityTester implements ITester {
    @Override
    public NormalDistributionTestBo test(double[] data) {
        double statistic = NormalityTest.dagostino_pearson_statistic(data);
        double pValue = NormalityTest.dagostino_pearson_pvalue(data.length);
        String judge = String.format(JUDGE_SUIT, alpha, pValue < alpha ? NO_CN : "");
        return new NormalDistributionTestBo("D'Agostino-Pearson Omnibus Normality校验", statistic, pValue, judge);
    }
}
