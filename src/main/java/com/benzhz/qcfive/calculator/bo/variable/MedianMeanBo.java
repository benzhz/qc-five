package com.benzhz.qcfive.calculator.bo.variable;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.calculator.pool.SpcChartTableConstantsPool.getConst;
import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_MEDIAN_MEAN_CN;
import static com.benzhz.qcfive.utils.CalculateUtil.*;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 中位数
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：MeanBo
 * @Date：2025/2/16 22:15
 * @Filename：MeanBo
 */
@Data
public class MedianMeanBo extends SPCCalBo {




    public MedianMeanBo(List<VariableBo> data, SPCCalBo range) {
        super();
        //子组容量
        int n = data.get(0).getSamples().size();
        double[] pointValues = new double[data.size()];
        double[] rPoints = new double[data.size()];
        List<SPCPointBo> points = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            VariableBo sampleVariableData = data.get(i);
            List<Double> samples = sampleVariableData.getSamples();
            String time = sampleVariableData.getSampleTime();
            //各个子组平均值
            pointValues[i] = getMedian(doubleListToArray(samples));
            SPCPointBo spcPointBo = new SPCPointBo(time, pointValues[i]);
            points.add(spcPointBo);
        }
        double Ax = getConst("A2~-", n);
        double rMean = range.getAverage();
        double mean = getMean(pointValues);
        super.setPoints(points);
        super.setAverage(mean);
        super.setUcl(add(mean, multiply(rMean, Ax)));
        super.setLcl(subtract(mean, multiply(rMean, Ax)));
        super.setLabel(CHART_MEDIAN_MEAN_CN);
        super.rule();
    }
}
