package com.benzhz.qcfive.templatebuilder.attribute;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.ExcelTemplateDto;
import com.benzhz.qcfive.templatebuilder.IExcelTemplateBuilder;
import org.springframework.stereotype.Component;

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

@Component(CHART_CATEGORY_ATTRIBUTE+BEAN_SUFFIX)
public class AttributeExcelTemplateBuilder  implements IExcelTemplateBuilder {

    List<String> P_NP = CollectionUtil.newArrayList(CHART_NP, CHART_P);
    List<String> C_U = CollectionUtil.newArrayList(CHART_C, CHART_U);

    @Override
    public ExcelTemplateDto build(SpcChart chartInfo) {


        // 动态配置单行表头
        List<List<String>> head = new ArrayList<>();
        // 生成的数据列表
        List<List<Object>> data = new ArrayList<>();
        String[] headerNames = new String[3];
        if (P_NP.contains(chartInfo.getChart())) {
            buildPTpl(headerNames, data);
        }
        if (C_U.contains(chartInfo.getChart())) {
            buildCTpl(headerNames, data);
        }
        for (String headerName : headerNames) {
            List<String> singleHeader = new ArrayList<>();
            singleHeader.add(headerName);
            head.add(singleHeader);
        }
        return new ExcelTemplateDto(head, data);
    }

    /**
     * NP,P图的excel模板
     * @param headerNames
     * @param data
     */
    private static void buildPTpl(String[] headerNames, List<List<Object>> data) {
        headerNames[0] = TMPL_TIME_FIELD;
        headerNames[1] = TMPL_SAMPLE_NUM;
        headerNames[2] = TMPL_SAMPLE_FAIL_NUM;

        // 第一行数据
        List<Object> row1 = new ArrayList<>();
        row1.add(TMPL_DATE);
        int num1 = RandomUtil.randomInt(1000);
        int num2 = RandomUtil.randomInt(1000);
        row1.add(Math.max(num1, num2));
        row1.add(Math.min(num1, num2));
        data.add(row1);
    }

    /**
     * C,U图的excel模板
     * @param headerNames
     * @param data
     */
    private static void buildCTpl(String[] headerNames, List<List<Object>> data) {
        headerNames[0] = TMPL_TIME_FIELD;
        headerNames[1] = TMPL_SAMPLE_NUM;
        headerNames[2] = TMPL_SAMPLE_DEFAULT_NUM;

        // 第一行数据
        List<Object> row1 = new ArrayList<>();
        row1.add(TMPL_DATE);
        row1.add(RandomUtil.randomInt(1000));
        row1.add(RandomUtil.randomInt(100));
        data.add(row1);


    }
}
