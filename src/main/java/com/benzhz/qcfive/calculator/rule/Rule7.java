package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 7：连续 15 点落在中心线两侧的 C 区内
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule7 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        double uc = cl + (ucl - cl) / 3;
        double lc = cl - (cl - lcl) / 3;
        boolean hasViolation = false;
        for (int i = 0; i <= data.size() - 15; i++) {
            boolean allInC = true;
            for (int j = i; j < i + 15; j++) {
                if (data.get(j).getValue() > uc || data.get(j).getValue() < lc) {
                    allInC = false;
                    break;
                }
            }
            if (allInC) {
                for (int k = i; k < i + 15; k++) {
                    data.get(k).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 7：连续 15 点落在中心线两侧的 C 区内";
        }
        return "";
    }
}
