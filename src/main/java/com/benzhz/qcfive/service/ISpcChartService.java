package com.benzhz.qcfive.service;

import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.SampleTableDto;
import com.benzhz.qcfive.pojo.dto.SpcDelDto;
import com.benzhz.qcfive.pojo.dto.SpcDto;
import com.benzhz.qcfive.pojo.dto.SpcSearchDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * spc控制图Service
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service
 * @Project：qc-five
 * @name：SpcService
 * @Date：2025/2/16 22:15
 * @Filename：SpcService
 */
public interface ISpcChartService {

    void importData(SampleTableDto sampleTableDto);

    SpcChart save(SpcDto dto);

    void delete(SpcDelDto dto);

    SpcChart getOne(SpcDto dto);

    List<SpcChart> list(SpcSearchDto dto);

    void downloadExcelTemplate(Long chartId, HttpServletResponse response) throws IOException;
}
