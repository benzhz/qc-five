package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import com.benzhz.qcfive.convert.SpcVariableChartConvert;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool.getConst;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_SINGE_POINT_MEAN_CN;
import static com.benzhz.qcfive.utils.CalculateUtil.*;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 单值图平均值
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：MeanBo
 * @Date：2025/2/16 22:15
 * @Filename：MeanBo
 */
@Data
public class SingePointMeanBo extends SPCCalBo {




    public SingePointMeanBo(List<VariableBo> data, SPCCalBo range) {
        List<Double> samples = data.stream().map(VariableBo::getSample).collect(Collectors.toList());
        List<SPCPointBo> points = SpcVariableChartConvert.INSTANCE.convertList(data);
        //移动极差
        double[] pointValues = doubleListToArray(samples);
        double mean = getMean(pointValues);
        //这里应该是特殊情况，E2常量没有n=1的值，网上的资料显示这里取了2.66,即n=2的值
        double E2 = getConst("E2", 2);
        double rMean = range.getAverage();
        super.setAverage(mean);
        super.setUcl(add(mean, multiply(rMean, E2)));
        super.setLcl(subtract(mean, multiply(rMean, E2)));
        super.setPoints(points);
        super.setLabel(CHART_SINGE_POINT_MEAN_CN);
        super.rule();
    }
}
