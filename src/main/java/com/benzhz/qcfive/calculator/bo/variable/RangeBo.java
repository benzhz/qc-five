package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool.getConst;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_RANGE_CN;
import static com.benzhz.qcfive.utils.CalculateUtil.*;
import static com.benzhz.qcfive.utils.Mutil.multiply;


/**
 * 极差
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：MeanBo
 * @Date：2025/2/16 22:15
 * @Filename：MeanBo
 */
@Data
public class RangeBo extends SPCCalBo {




    public RangeBo(List<VariableBo> data) {
        //子组容量
        int n = data.get(0).getSamples().size();
        double[] pointValues = new double[data.size()];
        List<SPCPointBo> points = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            VariableBo sampleVariableData = data.get(i);
            List<Double> samples = sampleVariableData.getSamples();
            String time = sampleVariableData.getSampleTime();
            //各个子组极差
            pointValues[i] = getRange(doubleListToArray(samples));
            SPCPointBo spcPointBo = new SPCPointBo(time, pointValues[i]);
            points.add(spcPointBo);
        }
        double D3 = getConst("D3", n);
        double D4 = getConst("D4", n);
        double rMean = getMean(pointValues);
        super.setPoints(points);
        super.setAverage(rMean);
        super.setLcl(multiply(rMean, D3));
        super.setUcl(multiply(rMean, D4));

        super.setLabel(CHART_RANGE_CN);
        super.rule();
    }
}
