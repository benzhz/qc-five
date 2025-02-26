package com.benzhz.qcfive.convert;

import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.SpcDto;
import com.benzhz.qcfive.pojo.vo.SPCChartVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 控制图基本信息转换
 * @Author：zhz
 * @Package：com.benzhz.qcfive.convert
 * @Project：qc-five
 * @name：SpcChartConvert
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartConvert
 */
@Mapper
public interface SpcChartConvert {

    SpcChartConvert INSTANCE = Mappers.getMapper(SpcChartConvert.class);

    SpcChart convert(SpcDto dto);

    @Mappings(
            @Mapping(source = "name", target = "label")
    )
    SPCChartVo convertToVo(SpcChart one);

    List<SPCChartVo> convertToVoList(List<SpcChart> list);



}
