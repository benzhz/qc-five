package com.benzhz.qcfive.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 样本数据表头
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SampleTableDto
 * @Date：2025/2/16 22:15
 * @Filename：SampleTableDto
 */
@Data
@Accessors(chain = true)
@Document("qc_spc_column")
public class SampleTableColumn {
    @MongoId
    private Long id;
    //表头标题
    private String title;
    //表头字段，antdv中使用
    private String dataIndex;
    //控制图id
    private Long chartId;
}
