package com.benzhz.qcfive.calculator.impl.attribute;


import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.attribute.AttributeBo;
import com.benzhz.qcfive.calculator.bo.attribute.CChartBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_C;

/**
 * 不 合 格 品 数(缺 陷)控 制 图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pool
 * @Project：qc-five
 * @name：CChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：CChartCalculator
 */
@Service(CHART_C)
public class CChartCalculator extends AbstractAttributeCalculator {

    @Override
    public SPCControlCharBo calculate(List<AttributeBo> sampleData) {
        return new SPCControlCharBo(new CChartBo(sampleData));
    }
}
