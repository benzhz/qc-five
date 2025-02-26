package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 2：连续 9 点落在中心线同一侧
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule2 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        int countAbove = 0;
        int countBelow = 0;
        boolean hasViolation = false;
        for (SPCPointBo point : data) {
            if (point.getValue() > cl) {
                countAbove++;
                countBelow = 0;
            } else {
                countBelow++;
                countAbove = 0;
            }
            if (countAbove >= 9) {
                for (int i = data.indexOf(point) - 8; i <= data.indexOf(point); i++) {
                    data.get(i).setError(true);
                }
                hasViolation = true;
            } else if (countBelow >= 9) {
                for (int i = data.indexOf(point) - 8; i <= data.indexOf(point); i++) {
                    data.get(i).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 2：连续 9 点落在中心线同一侧";
        }
        return "";
    }
}
