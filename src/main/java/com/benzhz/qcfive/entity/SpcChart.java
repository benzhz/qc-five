package com.benzhz.qcfive.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 控制图信息
 * @Author：zhz
 * @Package：com.benzhz.qcfive.entity
 * @Project：qc-five
 * @name：SpcChart
 * @Date：2025/2/16 22:15
 * @Filename：SpcChart
 */
@Data
@Accessors(chain = true)
@Document("spc_chart")
public class SpcChart {
    @MongoId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //名称
    private String name;
    //描述
    private String description;
    //控制图分类
    private String chartCategory;
    //控制图
    private String chart;
    //父节点id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    //子组容量
    private int subgroupSize;
    //单位
    private String unit;
    //产品规格
    private Double standard;
    //规格上限限
    private Double scope;

}
