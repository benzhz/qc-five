package com.benzhz.qcfive.pojo.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 样本数据表头对象
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SampleTableDto
 * @Date：2025/2/16 22:15
 * @Filename：SampleTableDto
 */
@Data
public class SampleTableColumnDto {
    //标题
    private String title;
    //字段名
    private String dataIndex;
}
