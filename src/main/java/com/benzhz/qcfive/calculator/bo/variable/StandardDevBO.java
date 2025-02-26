package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool.getConst;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_STANDARD_DEV_CN;
import static com.benzhz.qcfive.utils.CalculateUtil.*;
import static com.benzhz.qcfive.utils.CalculateUtil.getMean;
import static com.benzhz.qcfive.utils.Mutil.multiply;

/**
 * 标准差
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo
 * @Project：qc-five
 * @name：StandardDevBO
 * @Date：2025/2/16 22:15
 * @Filename：StandardDevBO
 */
public class StandardDevBO  extends SPCCalBo {



    public StandardDevBO(List<VariableBo> data) {
        List<SPCPointBo> points = new ArrayList<>(data.size());
        //子组容量
        int n = data.size();
        double[] pointValues = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            VariableBo sampleVariableData = data.get(i);
            List<Double> samples = sampleVariableData.getSamples();
            String time = sampleVariableData.getSampleTime();
            //各个子组标准差
            pointValues[i] = getStandardDeviation(doubleListToArray(samples));
            points.add(new SPCPointBo(time, pointValues[i]));
        }
        double B3 = getConst("B3", n);
        double B4 = getConst("B4", n);
        double rMean = getMean(pointValues);
        super.setAverage(rMean);
        super.setLcl(multiply(rMean, B3));
        super.setUcl(multiply(rMean, B4));
        super.setPoints(points);
        super.setLabel(CHART_STANDARD_DEV_CN);
        super.rule();
    }
}
