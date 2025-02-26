package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * 准则 3：连续 6 点递增或递减
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：Rule1
 * @Date：2025/2/16 22:15
 * @Filename：Rule1
 */
public class Rule3 implements SPCRule {
    @Override
    public String check(List<SPCPointBo> data, double cl, double ucl, double lcl) {
        int increasingCount = 1;
        int decreasingCount = 1;
        boolean hasViolation = false;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getValue() > data.get(i - 1).getValue()) {
                increasingCount++;
                decreasingCount = 1;
            } else if (data.get(i).getValue() < data.get(i - 1).getValue()) {
                decreasingCount++;
                increasingCount = 1;
            } else {
                increasingCount = 1;
                decreasingCount = 1;
            }
            if (increasingCount >= 6) {
                for (int j = i - 5; j <= i; j++) {
                    data.get(j).setError(true);
                }
                hasViolation = true;
            } else if (decreasingCount >= 6) {
                for (int j = i - 5; j <= i; j++) {
                    data.get(j).setError(true);
                }
                hasViolation = true;
            }
        }
        if (hasViolation) {
            return "准则 3：连续 6 点递增或递减";
        }
        return "";
    }
}
