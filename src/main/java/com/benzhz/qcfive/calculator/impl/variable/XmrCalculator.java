package com.benzhz.qcfive.calculator.impl.variable;

import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.variable.MoveRangeBo;
import com.benzhz.qcfive.calculator.bo.variable.SingePointMeanBo;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;
import com.benzhz.qcfive.calculator.bo.variable.XmrBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_XMR;

/**
 * 单值-移动极差控制图
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：XbarRChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：XbarRChartCalculator
 */
@Service(CHART_XMR)
public class XmrCalculator extends AbstractVariableCalculator {
    @Override
    public SPCControlCharBo calculate(List<VariableBo> data) {
        MoveRangeBo mRangeBo = new MoveRangeBo(data);
        return new XmrBo(new SingePointMeanBo(data, mRangeBo), mRangeBo);
    }
}
