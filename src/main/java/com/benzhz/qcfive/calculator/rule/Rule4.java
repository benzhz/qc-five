package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 4：连续 14 点中相邻点上下交替
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule4 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        if (data.size() < 14) {
            return "";
        }
        boolean hasViolation = false;
        for (int i = 0; i <= data.size() - 14; i++) {
            boolean isAlternating = true;
            for (int j = i; j < i + 13; j++) {
                boolean currentTrend = data.get(j + 1).getValue() > data.get(j).getValue();
                boolean nextTrend = data.get(j + 2).getValue() > data.get(j + 1).getValue();
                if (currentTrend == nextTrend) {
                    isAlternating = false;
                    break;
                }
            }
            if (isAlternating) {
                for (int k = i; k < i + 14; k++) {
                    data.get(k).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 4：连续 14 点中相邻点上下交替";
        }
        return "";
    }
}
