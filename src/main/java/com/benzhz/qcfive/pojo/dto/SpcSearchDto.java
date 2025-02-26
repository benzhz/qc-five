package com.benzhz.qcfive.pojo.dto;

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
public class SpcSearchDto {
    private Long id;
    private String name;
    private String description;
    private String chartType;
    private int subgroupSize;
}
