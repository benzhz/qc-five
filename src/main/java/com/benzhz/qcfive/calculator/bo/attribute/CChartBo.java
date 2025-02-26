package com.benzhz.qcfive.calculator.bo.attribute;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_C_CN;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 不合格品数(缺陷)控制图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.attribute
 * @Project：qc-five
 * @name：PChartBo
 * @Date：2025/2/16 22:15
 * @Filename：PChartBo
 */
@Data
public class CChartBo extends SPCCalBo {



    public CChartBo(List<AttributeBo> sampleData) {
        // 计算缺陷数总和
        double totalDefectNum = 0;
        List<SPCPointBo> points = new ArrayList<>();
        for (AttributeBo bo : sampleData) {
            totalDefectNum = add(bo.getDefectNum(), totalDefectNum);
            points.add(new SPCPointBo(bo.getSampleTime(), bo.getDefectNum()));
        }
        // 计算平均缺陷数
        double averageDefectNum = divide(totalDefectNum, sampleData.size());
        // 计算中心线（CL）
        double cl = averageDefectNum;
        // 计算上控制限（UCL）
        double ucl = add(averageDefectNum, multiply(3, Math.sqrt(averageDefectNum)));
        // 计算下控制限（LCL）
        double lcl = subtract(averageDefectNum, multiply(3, Math.sqrt(averageDefectNum)));
        // 确保下控制限不为负数
        lcl = Math.max(0, lcl);

        super.setPoints(points);
        super.setAverage(cl);
        super.setUcl(ucl);
        super.setLcl(lcl);
        super.setLabel(CHART_C_CN);
        super.rule();
    }
}
