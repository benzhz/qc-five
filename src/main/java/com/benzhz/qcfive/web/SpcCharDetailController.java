package com.benzhz.qcfive.web;

import com.benzhz.qcfive.common.pojo.CommonResult;
import com.benzhz.qcfive.pojo.dto.SpcChartDetailDto;
import com.benzhz.qcfive.pojo.vo.SPCResultVo;
import com.benzhz.qcfive.service.ISpcChartDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.benzhz.qcfive.common.pojo.CommonResult.success;

/**
 * spc控制图的参数Controller
 * @Author：zhz
 * @Package：com.benzhz.qcfive.web
 * @Project：qc-five
 * @name：SpcCharInfoController
 * @Date：2025/2/16 22:15
 * @Filename：SpcCharInfoController
 */
@RestController
@RequestMapping("/spc/info")
public class SpcCharDetailController {

    @Resource
    private ISpcChartDetailService spcChartInfoService;

    /**
     * 获取spc控制图的参数（图点线，cpk，正态校验）
     * @param dto
     * @return
     */
    @GetMapping("/getChartInfo")
    public CommonResult<SPCResultVo> getChartInfo(SpcChartDetailDto dto) {
        return success(spcChartInfoService.getChartInfo(dto));
    }
}
