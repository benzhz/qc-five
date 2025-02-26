package com.benzhz.qcfive.calculator.impl.variable;

import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.variable.MeanBo;
import com.benzhz.qcfive.calculator.bo.variable.StandardDevBO;
import com.benzhz.qcfive.calculator.bo.variable.VariableBo;
import com.benzhz.qcfive.calculator.bo.variable.XbarSBo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_XBARS;

/**
 * 均值-标准差控制图
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator
 * @Project：qc-five
 * @name：XbarRChartCalculator
 * @Date：2025/2/16 22:15
 * @Filename：XbarRChartCalculator
 */
@Service(CHART_XBARS)
public class XbarSCalculator extends AbstractVariableCalculator {
    @Override
    public SPCControlCharBo calculate(List<VariableBo> data) {
        StandardDevBO standardDevBO = new StandardDevBO(data);
        return new XbarSBo(new MeanBo(data,"A3",standardDevBO),standardDevBO);
    }
}
