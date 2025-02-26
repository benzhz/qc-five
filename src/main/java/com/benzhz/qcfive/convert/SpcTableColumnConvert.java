package com.benzhz.qcfive.convert;

import com.benzhz.qcfive.entity.SampleTableColumn;
import com.benzhz.qcfive.pojo.dto.SampleTableColumnDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 样本数据表头信息转换
 * @Author：zhz
 * @Package：com.benzhz.qcfive.convert
 * @Project：qc-five
 * @name：SpcChartConvert
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartConvert
 */
@Mapper
public interface SpcTableColumnConvert {

    SpcTableColumnConvert INSTANCE = Mappers.getMapper(SpcTableColumnConvert.class);

    List<SampleTableColumn> convertList(List<SampleTableColumnDto> columns);
}
