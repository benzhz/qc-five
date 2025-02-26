package com.benzhz.qcfive.calculator.bo.attribute;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_U_CN;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 单位不合格(缺陷)数控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.attribute
 * @Project：qc-five
 * @name：UChartBo
 * @Date：2025/2/16 22:15
 * @Filename：UChartBo
 */
@Data
public class UChartBo extends SPCCalBo {


    public UChartBo(List<AttributeBo> sampleData) {
        double nTotal = 0.0;
        double cTotal = 0.0;
        int k = sampleData.size();
        List<SPCPointBo> points = new ArrayList<>();
        for (int i = 0; i < sampleData.size(); i++) {
            AttributeBo attribute = sampleData.get(i);
            cTotal = add(cTotal, attribute.getDefectNum());
            nTotal = add(nTotal, attribute.getTotalNum());
            points.add(new SPCPointBo(attribute.getSampleTime(), attribute.getDefectRate()));
        }
        //各子组缺陷总数均值
        double uAvg = divide(cTotal, nTotal);
        double nAvg = divide(nTotal, k);
        //各子组总数均值
        super.setPoints(points);
        super.setAverage(uAvg);
        double temp = multiply(3, Math.sqrt(divide(uAvg, nAvg)));
        super.setUcl(add(uAvg, temp));
        super.setLcl(subtract(uAvg, temp));
        super.setLabel(CHART_U_CN);
        super.rule();
    }
}
