package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 6：连续 5 点中有 4 点落在中心线同一侧的 C 区以外
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule6 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        double uc = cl + (ucl - cl) / 3;
        double lc = cl - (cl - lcl) / 3;
        boolean hasViolation = false;
        for (int i = 0; i <= data.size() - 5; i++) {
            int countAbove = 0;
            int countBelow = 0;
            for (int j = i; j < i + 5; j++) {
                if (data.get(j).getValue() > uc) {
                    countAbove++;
                } else if (data.get(j).getValue() < lc) {
                    countBelow++;
                }
            }
            if (countAbove >= 4 || countBelow >= 4) {
                for (int k = i; k < i + 5; k++) {
                    data.get(k).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 6：连续 5 点中有 4 点落在中心线同一侧的 C 区以外";
        }
        return "";
    }
}
