package com.benzhz.qcfive.service.impl;

import com.benzhz.qcfive.calculator.INormalDistributionTestCalculator;
import com.benzhz.qcfive.calculator.ISPCChartCalculator;
import com.benzhz.qcfive.calculator.ISPCCpkCalculator;
import com.benzhz.qcfive.calculator.bo.NormalDistributionTestResultBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.SPCCpkBo;
import com.benzhz.qcfive.entity.SampleTableColumn;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.SpcChartDetailDto;
import com.benzhz.qcfive.pojo.vo.SPCResultVo;
import com.benzhz.qcfive.repository.SpcChartRepository;
import com.benzhz.qcfive.repository.SpcTableColumnRepository;
import com.benzhz.qcfive.repository.SpcTableDataSourceRepository;
import com.benzhz.qcfive.service.ISpcChartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.CHART_CATEGORY_VARIABLE;

/**
 * spc控制图参数Service
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.impl
 * @Project：qc-five
 * @name：ISpcChartInfoServiceImpl
 * @Date：2025/2/16 22:15
 * @Filename：ISpcChartInfoServiceImpl
 */
@Service
public class SpcChartDetailServiceImpl implements ISpcChartDetailService {

    @Autowired
    private Map<String, ISPCChartCalculator> variableCalculatorMap;
    @Resource
    private ISPCCpkCalculator spcCpkCalculator;
    @Resource
    private INormalDistributionTestCalculator normalDistributionTestCalculator;
    @Resource
    private SpcChartRepository spcChartRepository;
    @Resource
    private SpcTableDataSourceRepository spcTableDataSourceRepository;
    @Resource
    private SpcTableColumnRepository spcTableColumnRepository;

    /**
     * 获取spc控制图的参数（图点线，cpk，正态校验）
     *
     * @param dto
     */
    @Override
    public SPCResultVo getChartInfo(SpcChartDetailDto dto) {
        //查出控制图
        SpcChart chartInfo = spcChartRepository.getById(dto.getId());
        if (chartInfo == null || chartInfo.getParentId() == null) {
            return new SPCResultVo();
        }
        //查找样本数据
        List<Map<String, String>> tableData = spcTableDataSourceRepository.findByChartId(chartInfo.getId());
        if (tableData == null || tableData.isEmpty()) {
            return new SPCResultVo();
        }
        //查找样本数据表头
        List<SampleTableColumn> tableColumns = spcTableColumnRepository.findAllByChartId(dto.getId());
        //根据控制图类型获取计算对象
        ISPCChartCalculator chartCalculator = variableCalculatorMap.get(chartInfo.getChart());
        //计算控制图所需参数
        SPCControlCharBo chartBo = chartCalculator.calculate(chartInfo.getChart(), tableData);
        //计量型控制图，没有cpk计算，通常不是正态分布
        if (CHART_CATEGORY_VARIABLE.equals(chartInfo.getChartCategory())) {
            //计算cpk所需参数
            SPCCpkBo cpkBo = spcCpkCalculator.calculate(tableData, chartInfo);
            //正态性校验
            NormalDistributionTestResultBo testResultBo = normalDistributionTestCalculator.calculate(tableData);
            //返回结果
            return new SPCResultVo(chartBo, cpkBo, testResultBo, tableData, tableColumns);
        }
        //返回结果
        return new SPCResultVo(chartBo, tableData, tableColumns);
    }
}
