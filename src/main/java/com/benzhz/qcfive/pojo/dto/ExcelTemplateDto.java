package com.benzhz.qcfive.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：ExcelTemplateDto
 * @Date：2025/2/16 22:15
 * @Filename：ExcelTemplateDto
 */
@AllArgsConstructor
@Data
public class ExcelTemplateDto {
    List<List<String>> head ;
    List<List<Object>> data ;
}
