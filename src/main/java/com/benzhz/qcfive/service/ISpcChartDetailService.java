package com.benzhz.qcfive.service;

import com.benzhz.qcfive.pojo.dto.SpcChartDetailDto;
import com.benzhz.qcfive.pojo.vo.SPCResultVo;

/**
 * spc控制图参数Service
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service
 * @Project：qc-five
 * @name：SpcService
 * @Date：2025/2/16 22:15
 * @Filename：SpcService
 */
public interface ISpcChartDetailService {

    SPCResultVo getChartInfo(SpcChartDetailDto dto);
}
