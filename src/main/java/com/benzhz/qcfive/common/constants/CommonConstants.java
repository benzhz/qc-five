package com.benzhz.qcfive.common.constants;

import com.benzhz.qcfive.calculator.rule.*;

import java.util.regex.Pattern;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.common.constants
 * @Project：qc-five
 * @name：SPCChartConstants
 * @Date：2025/2/16 22:15
 * @Filename：SPCChartConstants
 */
public class CommonConstants {
    public static final String SAMPLE_D = "sample\\d+";

    public static final Pattern pattern = Pattern.compile(SAMPLE_D);
    //系统精度
    public static final  int SYSTEM_COMMON_PRECISION = 6;
    //最低抽检数量
    public static final int MIN_GROUP_SIZE = 5;

    //八大判异
    public static final RuleContext RULE_CONTEXT = new RuleContext(
            new Rule1(), new Rule2(), new Rule3(), new Rule4(), new Rule5(), new Rule6(), new Rule7(), new Rule8()
    );
}
