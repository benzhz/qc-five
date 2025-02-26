package com.benzhz.qcfive.calculator.rule;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.List;

/**
 * 八大判异
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.rule
 * @Project：qc-five
 * @name：SPCRule
 * @Date：2025/2/16 22:15
 * @Filename：SPCRule
 */
// 定义 SPC 规则接口
public interface SPCRule {
    String check(List<SPCPointBo> data, double cl, double ucl, double lcl);
}
