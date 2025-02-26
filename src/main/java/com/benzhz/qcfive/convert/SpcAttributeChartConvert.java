package com.benzhz.qcfive.convert;

import com.benzhz.qcfive.calculator.bo.attribute.AttributeBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 计数性控制图Bo转换
 * @Author：zhz
 * @Package：com.benzhz.qcfive.convert
 * @Project：qc-five
 * @name：SpcChartConvert
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartConvert
 */
@Mapper
public interface SpcAttributeChartConvert {

    SpcAttributeChartConvert INSTANCE = Mappers.getMapper(SpcAttributeChartConvert.class);

    AttributeBo covert01(Map<String, String> sampleData);

    List<AttributeBo> convertList01(List<Map<String, String>> sampleData);
}
