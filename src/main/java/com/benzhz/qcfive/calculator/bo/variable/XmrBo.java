package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import lombok.Data;

/**
 * 单值和移动极差图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo
 * @Project：qc-five
 * @name：XmrBo
 * @Date：2025/2/16 22:15
 * @Filename：XmrBo
 */
@Data
public class XmrBo extends SPCControlCharBo {

    public XmrBo(SPCCalBo... calBos) {
        super(calBos);
    }
}
