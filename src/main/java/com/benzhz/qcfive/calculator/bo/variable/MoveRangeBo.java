package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool.getConst;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_MOVE_RANGE_CN;
import static com.benzhz.qcfive.utils.CalculateUtil.*;
import static com.benzhz.qcfive.utils.Mutil.multiply;

/**
 * 移动极差
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo
 * @Project：qc-five
 * @name：MVBo
 * @Date：2025/2/16 22:15
 * @Filename：MVBo
 */
public class MoveRangeBo extends SPCCalBo {



    public MoveRangeBo(List<VariableBo> data) {
        List<Double> samples = data.stream().map(VariableBo::getSample).collect(Collectors.toList());
        List<SPCPointBo> points = new ArrayList<>(data.size());
        double[] pointValues = doubleListToArray(samples);
        //子组移动极差
        double[] movingRange = getMovingRange(pointValues);

        for (int i = 1; i < data.size(); i++) {
            points.add(new SPCPointBo(data.get(i).getSampleTime(), movingRange[i - 1]));
        }

        //这里应该是特殊情况，D3,D4常量没有n=1的值，网上的资料显示这里取了3.267,即n=2的值
        double D3 = getConst("D3", 2);
        double D4 = getConst("D4", 2);
        double rMean = getMean(movingRange);
        super.setPoints(points);
        super.setAverage(rMean);
        super.setLcl(multiply(rMean, D3));
        super.setUcl(multiply(rMean, D4));
        super.setLabel(CHART_MOVE_RANGE_CN);
        super.rule();
    }
}
