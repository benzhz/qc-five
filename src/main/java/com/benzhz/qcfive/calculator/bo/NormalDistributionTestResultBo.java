package com.benzhz.qcfive.calculator.bo;

import com.benzhz.qcfive.calculator.bo.variable.NormalProbabilityTestBo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 正态性检验参数
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.pojo.vo
 * @Project：qc-five
 * @name：SPCCpkVo
 * @Date：2025/2/16 22:15
 * @Filename：SPCCpkVo
 */

@AllArgsConstructor
@Data
public class NormalDistributionTestResultBo {
    //正态概率图数据
    private NormalProbabilityTestBo normalProbabilityChart;
    //正态性检验结果
    private List<NormalDistributionTestBo> testBos;
}
