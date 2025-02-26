package com.benzhz.qcfive.calculator.bo.attribute;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_P_CN;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 不合格品率的控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.attribute
 * @Project：qc-five
 * @name：PChartBo
 * @Date：2025/2/16 22:15
 * @Filename：PChartBo
 */
@Data
public class PChartBo extends SPCCalBo {


    public PChartBo(List<AttributeBo> dataList) {

        // 计算总样本数量和总不合格品数
        double totalSamples = 0;
        double totalFailures = 0;
        List<SPCPointBo> points = new ArrayList<>();

        for (AttributeBo attribute : dataList) {
            totalSamples = add(attribute.getTotalNum(), totalSamples);
            totalFailures = add(attribute.getFailNum(), totalFailures);
            points.add(new SPCPointBo(attribute.getSampleTime(), attribute.getFailRate()));
        }

        // 计算平均不合格品率（中心线 CL）
        double CL = divide(totalFailures, totalSamples);

        // 计算平均样本量
        double averageSampleSize = divide(totalSamples, dataList.size());

        // 计算上控制限（UCL）
        double UCL = add(CL, multiply(3, Math.sqrt(divide((multiply(CL, (subtract(1, CL)))), averageSampleSize))));

        // 计算下控制限（LCL）
        double LCL = subtract(CL, multiply(3, Math.sqrt(divide((multiply(CL, (subtract(1, CL)))), averageSampleSize))));

        // 确保下控制限不为负数
        LCL = Math.max(0, LCL);

        super.setPoints(points);
        super.setAverage(CL);
        super.setUcl(UCL);
        super.setLcl(LCL);
        super.setLabel(CHART_P_CN);
        super.rule();
    }
}
