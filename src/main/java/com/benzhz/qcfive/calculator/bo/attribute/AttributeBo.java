package com.benzhz.qcfive.calculator.bo.attribute;

import lombok.Data;

/**
 * 计数性样本入参
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.attribute
 * @Project：qc-five
 * @name：AttributeBo
 * @Date：2025/2/16 22:15
 * @Filename：AttributeBo
 */
@Data
public class AttributeBo {
    //抽检时间
    private String sampleTime;
    //样本数量
    private Double totalNum;
    //不合格品数
    private Double failNum;
    //不合格品率
    private Double failRate;
    //缺陷数
    private Double defectNum;
    //单位缺陷率
    private Double defectRate;
}
