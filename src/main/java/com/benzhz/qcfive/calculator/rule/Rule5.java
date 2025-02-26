package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 5：连续 3 点中有 2 点落在中心线同一侧的 B 区以外
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule5 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        double ub = cl + (ucl - cl) * 2 / 3;
        double lb = cl - (cl - lcl) * 2 / 3;
        boolean hasViolation = false;
        for (int i = 0; i <= data.size() - 3; i++) {
            int countAbove = 0;
            int countBelow = 0;
            for (int j = i; j < i + 3; j++) {
                if (data.get(j).getValue() > ub) {
                    countAbove++;
                } else if (data.get(j).getValue() < lb) {
                    countBelow++;
                }
            }
            if (countAbove >= 2 || countBelow >= 2) {
                for (int k = i; k < i + 3; k++) {
                    data.get(k).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 5：连续 3 点中有 2 点落在中心线同一侧的 B 区以外";
        }
        return "";
    }
}
