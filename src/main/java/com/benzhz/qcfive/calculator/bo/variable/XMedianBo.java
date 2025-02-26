package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import lombok.Data;

/**
 * 中位数图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo
 * @Project：qc-five
 * @name：XTildeBo
 * @Date：2025/2/16 22:15
 * @Filename：XTildeBo
 */
@Data
public class XMedianBo extends SPCControlCharBo {

    public XMedianBo(SPCCalBo... calBos) {
        super(calBos);
    }
}
