package com.benzhz.qcfive.calculator.bo.attribute;

import com.benzhz.qcfive.calculator.bo.SPCCalBo;
import com.benzhz.qcfive.calculator.bo.SPCPointBo;
import com.benzhz.qcfive.common.exception.ServiceException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_NP_CN;
import static com.benzhz.qcfive.common.exception.enums.GlobalErrorCodeConstants.NP_NUM_NOT_SAME;
import static com.benzhz.qcfive.utils.Mutil.*;

/**
 * 不合格品数的控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.bo.attribute
 * @Project：qc-five
 * @name：PChartBo
 * @Date：2025/2/16 22:15
 * @Filename：PChartBo
 */
@Data
public class NPChartBo extends SPCCalBo {



    //fixme 暂时最多先保留6位小数

    public NPChartBo(List<AttributeBo> dataList) {
        // 检查样本数量是否一致（NP 图要求样本量固定）
        double firstSampleSize = dataList.get(0).getTotalNum();

        for (AttributeBo bo : dataList) {
            if (!bo.getTotalNum().equals(firstSampleSize)) {
                throw new ServiceException(NP_NUM_NOT_SAME);
            }
        }

        // 计算总不合格品数
        double totalFailures = 0;
        List<SPCPointBo> points = new ArrayList<>();
        for (AttributeBo attribute : dataList) {
            totalFailures = add(attribute.getFailNum(), totalFailures);
            points.add(new SPCPointBo(attribute.getSampleTime(), attribute.getFailNum()));
        }

        // 计算平均不合格品数（中心线 CL）
        double CL = totalFailures / dataList.size();
        // 计算上控制限（UCL）
        double UCL = add(CL, 3 * Math.sqrt(CL * (1 - (CL / firstSampleSize))));
        // 计算下控制限（LCL）
        double LCL = CL - 3 * Math.sqrt(CL * (1 - (CL / firstSampleSize)));
        // 确保下控制限不为负数
        LCL = Math.max(0, LCL);

        super.setPoints(points);
        super.setAverage(CL);
        super.setUcl(UCL);
        super.setLcl(LCL);
        super.setLabel(CHART_NP_CN);
        super.rule();

    }
}
