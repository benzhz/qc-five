package com.benzhz.qcfive.calculator.bo.variable;

import lombok.Data;

import java.util.List;

/**
 * 计量性样本入参
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SampleData
 * @Date：2025/2/16 22:15
 * @Filename：SampleData
 */
@Data
public class VariableBo {
    //抽检时间
    private String sampleTime;
    //多样本数据集合
    private List<Double> samples;
    //单样本数据集合
    private double sample;
}
