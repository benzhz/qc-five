package com.benzhz.qcfive.calculator.impl.variable;

import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.variable.MedianMeanBo;
import com.benzhz.qcfive.calculator.bo.variable.RangeBo;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;
import com.benzhz.qcfive.calculator.bo.variable.XMedianBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_XMEDIAN;

/**
 * 中位数-极差控制图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：XbarRChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：XbarRChartCalculator
 */
@Service(CHART_XMEDIAN)
public class XMedianCalculator extends AbstractVariableCalculator {
    @Override
    public SPCControlCharBo calculate(List<VariableBo> data) {
        RangeBo rangeBo = new RangeBo(data);
        return new XMedianBo(new MedianMeanBo(data,rangeBo),rangeBo);
    }
}
