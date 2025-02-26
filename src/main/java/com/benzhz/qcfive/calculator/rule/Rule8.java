package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 8：连续 8 点落在中心线两侧且无一点在 C 区内
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule8 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        double uc = cl + (ucl - cl) / 3;
        double lc = cl - (cl - lcl) / 3;
        boolean hasViolation = false;
        for (int i = 0; i <= data.size() - 8; i++) {
            boolean allOutsideC = true;
            boolean hasAbove = false;
            boolean hasBelow = false;
            for (int j = i; j < i + 8; j++) {
                if (data.get(j).getValue() >= lc && data.get(j).getValue() <= uc) {
                    allOutsideC = false;
                    break;
                }
                if (data.get(j).getValue() > cl) {
                    hasAbove = true;
                } else {
                    hasBelow = true;
                }
            }
            if (allOutsideC && hasAbove && hasBelow) {
                for (int k = i; k < i + 8; k++) {
                    data.get(k).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 8：连续 8 点落在中心线两侧且无一点在 C 区内";
        }
        return "";
    }
}
