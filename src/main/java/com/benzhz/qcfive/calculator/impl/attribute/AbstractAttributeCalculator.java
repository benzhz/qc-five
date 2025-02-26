package com.benzhz.qcfive.calculator.impl.attribute;

import com.benzhz.qcfive.calculator.ISPCChartCalculator;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.attribute.AttributeBo;
import com.benzhz.qcfive.convert.SpcAttributeChartConvert;

import java.util.List;
import java.util.Map;

import static com.benzhz.qcfive.utils.Mutil.divide;

/**
 * 计数型控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：AbstractAttributeSPCChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：AbstractAttributeSPCChartCalculator
 */
public abstract class AbstractAttributeCalculator implements ISPCChartCalculator {


    /**
     * 参数适配
     *
     * @param
     * @return
     */
    public SPCControlCharBo calculate(String chart, List<Map<String, String>> sampleData) {
        List<AttributeBo> attributeBos = SpcAttributeChartConvert.INSTANCE.convertList01(sampleData);
        attributeBos.forEach(attributeBo -> {
            // 不良品率
            if (attributeBo.getTotalNum() != null && attributeBo.getTotalNum() > 0 && attributeBo.getFailNum() != null) {
                attributeBo.setFailRate(divide(attributeBo.getFailNum(), attributeBo.getTotalNum()));
            }
            //单位缺陷数
            if (attributeBo.getTotalNum() != null && attributeBo.getTotalNum() > 0 && attributeBo.getDefectNum() != null) {
                attributeBo.setDefectRate(divide(attributeBo.getDefectNum(), attributeBo.getTotalNum()));
            }
        });
        return this.calculate(attributeBos);
    }

    /**
     * 控制图计算
     *
     * @param
     * @return
     */
    public abstract SPCControlCharBo calculate(List<AttributeBo> sampleData);

}
