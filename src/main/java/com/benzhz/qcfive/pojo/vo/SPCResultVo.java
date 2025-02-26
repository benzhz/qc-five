package com.benzhz.qcfive.pojo.vo;

import com.benzhz.qcfive.calculator.bo.NormalDistributionTestResultBo;
import com.benzhz.qcfive.calculator.bo.SPCControlCharBo;
import com.benzhz.qcfive.calculator.bo.SPCCpkBo;
import com.benzhz.qcfive.entity.SampleTableColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 控制图参数(图点线，正态性检验，cpk)
 * @Author：zhz
 * @Package：com.benzhz.qcfive.calculator.pojo
 * @Project：qc-five
 * @name：SPCCalculationResult
 * @Date：2025/2/16 22:15
 * @Filename：SPCCalculationResult
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SPCResultVo {

    //spc控制图相关值
    private SPCControlCharBo controlChar;

    //spc cpk相关产生
    private SPCCpkBo cpk;

    //正态性检验
    private NormalDistributionTestResultBo test;

    //样本数据
    private List<Map<String, String>> tableData;

    //样本数据表头
    private List<SampleTableColumn> tableColumns;

    public SPCResultVo(SPCControlCharBo controlChar, List<Map<String, String>> tableData, List<SampleTableColumn> tableColumns) {
        this.controlChar = controlChar;
        this.tableData = tableData;
        this.tableColumns = tableColumns;
    }
}
