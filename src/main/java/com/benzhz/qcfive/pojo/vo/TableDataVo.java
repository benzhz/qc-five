package com.benzhz.qcfive.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 样本数据表信息
 * @Author：zhz
 * @Package：com.benzhz.qcfive.vo
 * @Project：qc-five
 * @name：TableDataVo
 * @Date：2025/2/16 22:15
 * @Filename：TableDataVo
 */
@AllArgsConstructor
@Data
public class TableDataVo {
    //表头字段
    private List<ColumnVo> columns;
    //样本数据
    private List<Map<String, String>> data;
}
