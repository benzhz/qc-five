package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 1：一点落在 A 区以外
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule1 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        boolean hasViolation = false;
        for (SPCPointBo point : data) {
            if (point.getValue() > ucl || point.getValue() < lcl) {
                point.setError(true);
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 1：存在一点落在 A 区以外";
        }
        return "";
    }
}
