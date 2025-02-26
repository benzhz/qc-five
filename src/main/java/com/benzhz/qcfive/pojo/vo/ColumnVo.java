package com.benzhz.qcfive.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 样本数据表头对象
 * @Author：zhz
 * @Package：com.benzhz.qcfive.vo
 * @Project：qc-five
 * @name：ColumnVo
 * @Date：2025/2/16 22:15
 * @Filename：ColumnVo
 */
@AllArgsConstructor
@Data
public class ColumnVo {
    //字段名
    private String field;
    //表头标题
    private String title;
}
