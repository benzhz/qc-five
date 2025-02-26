package com.benzhz.qcfive.pojo.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SampleTableDto
 * @Date：2025/2/16 22:15
 * @Filename：SampleTableDto
 */
@Data
public class SampleTableDto {

    private Long chartId;

    private String chart;

    private String title;

    private List<Map<String, String>> dataSource;

    private List<SampleTableColumnDto> columns;
}
