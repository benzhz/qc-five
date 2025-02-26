package com.benzhz.qcfive.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * 每个控制图的基本信息
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.vo
 * @Project：qc-five
 * @name：SpcChartVo
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartVo
 */
@Data
public class SPCChartVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //名称
    private String name;
    private String label;
    //秒速
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
    //规格上下限
    private Double scope;
    private List<SPCChartVo> children;
}
