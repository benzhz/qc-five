package com.benzhz.qcfive.calculator.impl.variable;

import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.variable.MeanBo;
import com.benzhz.qcfive.calculator.bo.variable.RangeBo;
import com.benzhz.qcfive.calculator.bo.variable.XbarRBo;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_XBARR;

/**
 * 均值-极差控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：XbarRChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：XbarRChartCalculator
 */
@Service(CHART_XBARR)
public class XbarRCalculator extends AbstractVariableCalculator {
    @Override
    public SPCControlCharBo calculate(List<VariableBo> data) {
        RangeBo rangeBo = new RangeBo(data);
        return new XbarRBo(new MeanBo(data,"A2",rangeBo),rangeBo);
    }
}
