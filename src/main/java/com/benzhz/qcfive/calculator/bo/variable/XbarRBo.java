package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import lombok.Data;

/**
 * 均值和极差图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：SpcCalculationResult
 * @Date：2025/2/16 22:15
 * @Filename：SpcCalculationResult
 */
@Data
public class XbarRBo extends SPCControlCharBo {

    public XbarRBo(SPCCalBo... calBos) {
        super(calBos);
    }
}
