package com.benzhz.qcfive.calculator.bo;

import com.benzhz.qcfive.utils.Mutil;
import lombok.Data;

import static com.benzhz.qcfive.common.constants.CommonConstants.SYSTEM_COMMON_PRECISION;

/**
 * 正态性检验参数
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.vo
 * @Project：qc-five
 * @name：SPCCpkVo
 * @Date：2025/2/16 22:15
 * @Filename：SPCCpkVo
 */

@Data
public class NormalDistributionTestBo {

    //检验方法
    private String testMethod;
    //统计量
    private Double statistic;
    //p值
    private Double pValue;
    //判定
    private String judge;

    public NormalDistributionTestBo(String testMethod, Double statistic, Double pValue, String judge) {
        this.testMethod = testMethod;
        this.statistic = Mutil.round(statistic,SYSTEM_COMMON_PRECISION);
        this.pValue =  Mutil.round(pValue,SYSTEM_COMMON_PRECISION);
        this.judge = judge;
    }
}
