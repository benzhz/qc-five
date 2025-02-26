package com.benzhz.qcfive.templatebuilder.variable;

import cn.hutool.core.util.RandomUtil;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.ExcelTemplateDto;
import com.benzhz.qcfive.templatebuilder.IExcelTemplateBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.benzhz.qcfive.common.constants.SPCChartConstants.*;
import static com.benzhz.qcfive.templatebuilder.IExcelTemplateBuilder.BEAN_SUFFIX;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.templatebuilder.attribute
 * @Project：qc-five
 * @name：ExcelTemplateBuilder
 * @Date：2025/2/16 22:15
 * @Filename：ExcelTemplateBuilder
 */
@Component(CHART_CATEGORY_VARIABLE+BEAN_SUFFIX)
public class VariableExcelTemplateBuilder implements IExcelTemplateBuilder {



    @Override
    public ExcelTemplateDto build(SpcChart chartInfo) {

        int subgroupSize = chartInfo.getSubgroupSize();
        // 动态配置单行表头
        List<List<String>> head = new ArrayList<>();
        String[] headerNames = new String[subgroupSize+1];
        headerNames[0] = TMPL_TIME_FIELD;
        // 生成的数据列表
        List<List<Object>> data = new ArrayList<>();
        // 第一行数据
        List<Object> row1 = new ArrayList<>();
        row1.add(TMPL_DATE);
        data.add(row1);
        for (int i = 1; i <= subgroupSize; i++) {
            if (chartInfo.getSubgroupSize() == 1) {
                headerNames[i] = TMPL_FIELD;
            } else {
                headerNames[i] = TMPL_FIELD + i;
            }
            double randomValue = chartInfo.getStandard() + BigDecimal.valueOf(RandomUtil.randomDouble(0, chartInfo.getScope())).setScale(2, RoundingMode.HALF_UP).doubleValue();
            row1.add(randomValue);
        }
        for (String headerName : headerNames) {
            List<String> singleHeader = new ArrayList<>();
            singleHeader.add(headerName);
            head.add(singleHeader);
        }
        return new ExcelTemplateDto(head, data);
    }


}
