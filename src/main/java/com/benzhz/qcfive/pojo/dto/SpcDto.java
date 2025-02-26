package com.benzhz.qcfive.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SpcChartDto
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartDto
 */
@Data
public class SpcDto {
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long id;
    private String name;
    private String description;
    private String chartCategory;
    private String chart;
    private Long parentId;
    private Integer subgroupSize;
    //单位
    private String unit;
    //产品规格
    private Double standard;
    //规格上限限
    private Double scope;
}
