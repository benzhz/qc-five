package com.benzhz.qcfive.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.dto
 * @Project：qc-five
 * @name：SpcChartDto
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartDto
 */
@Data
public class SpcDelDto {
    @NotNull(message = "id不能为空")
    private Long id;

}
