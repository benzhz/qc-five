package com.benzhz.qcfive.templatebuilder;

import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.ExcelTemplateDto;

/**
 * @Author：zhz
 * @Package：com.benzhz.qcfive.templatebuilder
 * @Project：qc-five
 * @name：IExcelTemplateBuilder
 * @Date：2025/2/16 22:15
 * @Filename：IExcelTemplateBuilder
 */
public interface IExcelTemplateBuilder {
    String COLUMN_PREFIX = "field";
    String BEAN_SUFFIX = "TPL";
    String TMPL_DATE = "2025/1/1";
    String TMPL_TIME_FIELD = "抽检时间";
    String TMPL_FIELD = "样本";
    String TMPL_SAMPLE_NUM = "样本数量";
    String TMPL_SAMPLE_DEFAULT_NUM = "缺陷数";
    String TMPL_SAMPLE_FAIL_NUM = "不合格品数";

    ExcelTemplateDto build(SpcChart chartInfo);
}
