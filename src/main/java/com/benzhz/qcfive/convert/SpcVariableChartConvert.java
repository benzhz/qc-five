package com.benzhz.qcfive.convert;

import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 计量性控制图Bo转换
 * @Author：zhz
 * @Package：com.benzhz.qcfive.convert
 * @Project：qc-five
 * @name：SpcChartConvert
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartConvert
 */
@Mapper
public interface SpcVariableChartConvert {

    SpcVariableChartConvert INSTANCE = Mappers.getMapper(SpcVariableChartConvert.class);


    @Mappings({@Mapping(source = "sample", target = "value"), @Mapping(source = "sampleTime", target = "axis")})
    SPCPointBo covert01(VariableBo data);

    List<SPCPointBo> convertList(List<VariableBo> data);


}
