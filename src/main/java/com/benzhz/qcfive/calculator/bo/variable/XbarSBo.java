package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import lombok.Data;

/**
 * 均值和标准差图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：SpcCalculationResult
 * @Date：2025/2/16 22:15
 * @Filename：SpcCalculationResult
 */

@Data
public class XbarSBo extends SPCControlCharBo {

    public XbarSBo(SPCCalBo... calBos) {
        super(calBos);
    }
}
