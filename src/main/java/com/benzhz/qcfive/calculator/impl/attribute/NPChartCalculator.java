package com.benzhz.qcfive.calculator.impl.attribute;


import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.attribute.AttributeBo;
import com.benzhz.qcfive.calculator.bo.attribute.NPChartBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_NP;

/**
 * 不 合 格 品 数 的 控 制 图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pool
 * @Project：qc-five
 * @name：CChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：CChartCalculator
 */
@Service(CHART_NP)
public class NPChartCalculator extends AbstractAttributeCalculator {

    @Override
    public SPCControlCharBo calculate(List<AttributeBo> sampleData) {
        return new SPCControlCharBo(new NPChartBo(sampleData));
    }
}
