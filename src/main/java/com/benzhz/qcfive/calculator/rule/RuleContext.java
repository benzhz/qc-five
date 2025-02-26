package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * spc判异执行
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：RuleContext
 * @Date：2025/2/16 22:15
 * @Filename：RuleContext
 */

public class RuleContext {

    private final List<SPCRule> rules = new ArrayList<>();

    public RuleContext(SPCRule... ruleArray) {
        Collections.addAll(rules, ruleArray);
    }

    //执行判异
    public List<String> executeRules(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        List<String> results = new ArrayList<>();
        for (SPCRule rule : rules) {
            String result = rule.check(data, cl, ucl, lcl);
            if (!StringUtils.isEmpty(result)) {
                results.add(result);
            }
        }
        return results;
    }

}
