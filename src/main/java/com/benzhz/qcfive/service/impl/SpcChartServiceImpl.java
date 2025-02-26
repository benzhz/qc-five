package com.benzhz.qcfive.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.benzhz.qcfive.common.exception.ServiceException;
import com.benzhz.qcfive.convert.SpcChartConvert;
import com.benzhz.qcfive.convert.SpcTableColumnConvert;
import com.benzhz.qcfive.entity.SampleTableColumn;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.*;
import com.benzhz.qcfive.repository.SpcChartRepository;
import com.benzhz.qcfive.repository.SpcTableColumnRepository;
import com.benzhz.qcfive.repository.SpcTableDataSourceRepository;
import com.benzhz.qcfive.service.ISpcChartService;
import com.benzhz.qcfive.templatebuilder.IExcelTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static com.benzhz.qcfive.common.constants.CommonConstants.*;
import static com.benzhz.qcfive.common.exception.enums.GlobalErrorCodeConstants.SAMPLE_NUM_NOT_SUIT;
import static com.benzhz.qcfive.templatebuilder.IExcelTemplateBuilder.BEAN_SUFFIX;
import static com.benzhz.qcfive.utils.AssertUtil.isMatch;

/**
 * spc控制图Service
 *
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.impl
 * @Project：qc-five
 * @name：SpcServiceImpl
 * @Date：2025/2/16 22:15
 * @Filename：SpcServiceImpl
 */
@Service
public class SpcChartServiceImpl implements ISpcChartService {

    public static final String TMPL_SHEET = "样本数据";
    public static final String TMPL_FILE_NAME = "%s_样本模板.xlsx";

    @Resource
    private SpcChartRepository spcChartRepository;

    @Resource
    private SpcTableColumnRepository spcTableColumnRepository;

    @Resource
    private SpcTableDataSourceRepository spcTableDataSourceRepository;

    @Autowired
    private Map<String, IExcelTemplateBuilder> tplBuilderMap;


    /**
     * 导入样本数据
     *
     * @param sampleTableDto
     * @return
     */
    @Override
    @Transactional
    public void importData(SampleTableDto sampleTableDto) {
        List<SampleTableColumn> sampleTableColumns = SpcTableColumnConvert.INSTANCE.convertList(sampleTableDto.getColumns());
        sampleTableColumns.forEach(spcTableColumn -> {
            spcTableColumn.setId(IdUtil.getSnowflakeNextId());
            spcTableColumn.setChartId(sampleTableDto.getChartId());
        });
        SpcChart chartInfo = spcChartRepository.getById(sampleTableDto.getChartId());
        //覆盖旧数据
        clearOld(sampleTableDto.getChartId());
        //保存表头
        spcTableColumnRepository.saveAll(sampleTableColumns);
        //保存样本数据
        List<Map<String, String>> tableData = sampleTableDto.getDataSource();
        //样本数据校验
        assertSampleData(tableData, chartInfo.getSubgroupSize());
        tableData.forEach(spcTableDataSource -> {
            spcTableDataSource.put("chartId", sampleTableDto.getChartId().toString());
        });
        spcTableDataSourceRepository.batchSave(tableData);

    }

    /**
     * 样本数据校验
     *
     * @param tableData
     * @param subgroupSize
     */
    private void assertSampleData(List<Map<String, String>> tableData, int subgroupSize) {
        long count = tableData.get(0).keySet().stream().filter(key -> isMatch(key, pattern)).count();
        //检查样本数字是不是数字
        checkData(tableData);
        if (tableData.size() <= MIN_GROUP_SIZE) {
            throw new ServiceException(500, "抽检数量过少（需大于" + MIN_GROUP_SIZE + "组），请重新导入！");
        }
        if (count != subgroupSize) {
            throw new ServiceException(SAMPLE_NUM_NOT_SUIT, subgroupSize);
        }
    }

    /**
     * 检查样本数字是不是数字
     * @param dataList
     */
    public static void checkData(List<Map<String, String>> dataList) {
        int index = 0;
        for (Map<String, String> map : dataList) {
            index++;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.matches(SAMPLE_D)) {
                    try {
                        Double.parseDouble(value);
                    } catch (NumberFormatException e) {
                        throw new ServiceException(500, "第" + index + "行的样本数据 " + value + " 不是数字");
                    }
                }
            }
        }
    }

    /**
     * 清理旧数据
     */
    private void clearOld(Long chartId) {
        spcTableColumnRepository.deleteAllByChartId(chartId);
        spcTableDataSourceRepository.deleteAllByChartId(chartId);
    }

    @Override
    public SpcChart save(SpcDto dto) {
        SpcChart spcChart = SpcChartConvert.INSTANCE.convert(dto);
        if (spcChart.getId() == null) {
            spcChart.setId(IdUtil.getSnowflakeNextId());
        }
        spcChartRepository.save(spcChart);
        return spcChart;
    }

    /**
     * 删除spc控制图
     *
     * @param dto
     * @return
     */
    @Override
    public void delete(SpcDelDto dto) {
        spcChartRepository.deleteById(dto.getId());
    }

    /**
     * 获取一个spc控制图信息
     *
     * @param dto
     * @return
     */
    @Override
    public SpcChart getOne(SpcDto dto) {
        return spcChartRepository.getById(dto.getId());
    }

    /**
     * 获取spc控制图树
     *
     * @param dto
     * @return
     */
    @Override
    public List<SpcChart> list(SpcSearchDto dto) {
        if (StrUtil.isNotBlank(dto.getName())) {
            return spcChartRepository.findByNameLike(dto.getName());
        } else {
            return spcChartRepository.findAll();
        }
    }

    /**
     * 生成样本数据导入模板
     */
    @Override
    public void downloadExcelTemplate(Long chartId, HttpServletResponse response) throws IOException {
        SpcChart chartInfo = spcChartRepository.getById(chartId);

        //构建模板信息
        IExcelTemplateBuilder excelTemplateBuilder = tplBuilderMap.get(chartInfo.getChartCategory() + BEAN_SUFFIX);
        ExcelTemplateDto tplInfo = excelTemplateBuilder.build(chartInfo);

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(String.format(TMPL_FILE_NAME, chartInfo.getName(), TMPL_SHEET), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // 写入 Excel 并输出到响应流
        EasyExcel.write(response.getOutputStream()).head(tplInfo.getHead()).sheet(TMPL_SHEET).doWrite(tplInfo.getData());
    }
}
