package com.benzhz.qcfive.calculator.bo;

import lombok.Data;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo
 * @Project：qc-five
 * @name：SPCPointBO
 * @Date：2025/2/16 22:15
 * @Filename：SPCPointBO
 */
@Data
public class SPCPointBo {
    //x轴
    private String axis;
    //y轴
    private double value;
    //是否为异常点
    private Boolean error;

    public SPCPointBo(String axis, double value) {
        this.axis = axis;
        this.value = value;
    }
}
