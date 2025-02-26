package com.benzhz.qcfive.calculator.bo;

import com.benzhz.qcfive.utils.Mutil;
import lombok.Data;

import static com.benzhz.qcfive.common.constants.CommonConstants.SYSTEM_COMMON_PRECISION;

/**
 * cpk指标
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.vo
 * @Project：qc-five
 * @name：SPCCpkVo
 * @Date：2025/2/16 22:15
 * @Filename：SPCCpkVo
 */
@Data
public class SPCCpkQuotaBo {

    //指标名
    private String name;
    //整体值
    private double gobleValue;
    //组内值
    private double groupValue;

    public SPCCpkQuotaBo(String name, Double gobleValue, Double groupValue) {
        this.name = name;
        this.gobleValue = Mutil.round(gobleValue, SYSTEM_COMMON_PRECISION);
        if (groupValue != null) {
            this.groupValue = Mutil.round(groupValue, SYSTEM_COMMON_PRECISION);
        }
    }
}
