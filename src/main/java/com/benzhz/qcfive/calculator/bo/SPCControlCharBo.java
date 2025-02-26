package com.benzhz.qcfive.calculator.bo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 控制图参数集合
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：SPCCalculationResult
 * @Date：2025/2/16 22:15
 * @Filename：SPCCalculationResult
 */
@Data
public class SPCControlCharBo {

    //各个控制图参数
    private List<SPCCalBo> calBos;

    public SPCControlCharBo() {
    }

    public SPCControlCharBo(SPCCalBo... calBos) {

        this.calBos = Arrays.asList(calBos);
    }
}
