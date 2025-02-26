package com.benzhz.qcfive.web;

import com.benzhz.qcfive.common.pojo.CommonResult;
import com.benzhz.qcfive.convert.SpcChartConvert;
import com.benzhz.qcfive.entity.SpcChart;
import com.benzhz.qcfive.pojo.dto.SampleTableDto;
import com.benzhz.qcfive.pojo.dto.SpcDelDto;
import com.benzhz.qcfive.pojo.dto.SpcDto;
import com.benzhz.qcfive.pojo.dto.SpcSearchDto;
import com.benzhz.qcfive.pojo.vo.SPCChartVo;
import com.benzhz.qcfive.service.ISpcChartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.benzhz.qcfive.common.pojo.CommonResult.success;

/**
 * spc控制图contoller
 * @Author：zhz
 * @Package：com.benzhz.qcfive.web
 * @Project：qc-five
 * @name：SpcController
 * @Date：2025/2/16 22:15
 * @Filename：SpcController
 */
@RestController
@RequestMapping("/spc")
public class SpcChartController {


    @Resource
    private ISpcChartService spcChartService;


    /**
     * 下载excel模板
     * @param chartId
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadExcelTemplate")
    public void downloadExcelTemplate(@RequestParam("chartId") Long chartId, HttpServletResponse response) throws IOException {
        spcChartService.downloadExcelTemplate(chartId,response);
    }


    /**
     * 导入样本数据
     * @param sampleTableDto
     * @return
     */
    @PostMapping("/import-data")
    public CommonResult<Boolean> importData(@RequestBody SampleTableDto sampleTableDto) {
        spcChartService.importData(sampleTableDto);
        return success(true);
    }

    /**
     * 创建或更新spc
     * @param dto
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public CommonResult<SpcChart> save(@RequestBody SpcDto dto) {
        return success(spcChartService.save(dto));
    }

    /**
     * 删除spc控制图
     * @param dto
     * @return
     */
    @PostMapping("/delete")
    public CommonResult<Boolean> delete(@Valid @RequestBody SpcDelDto dto) {
        spcChartService.delete(dto);
        return success(true);
    }

    /**
     * 获取一个spc控制图信息
     * @param dto
     * @return
     */
    @GetMapping("/getOne")
    public CommonResult<SPCChartVo> getOne(SpcDto dto) {
        SpcChart one = spcChartService.getOne(dto);
        return success(SpcChartConvert.INSTANCE.convertToVo(one));
    }
    /**
     * 获取spc控制图树
     * @param dto
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<SPCChartVo>> list(SpcSearchDto dto) {
        List<SpcChart> list = spcChartService.list(dto);
        List<SPCChartVo> spcChartVos = SpcChartConvert.INSTANCE.convertToVoList(list);
        return success(buildTree(spcChartVos));
    }

    /**
     * 构建树形结构
     *
     * @param spcChartVos
     * @return
     */
    public List<SPCChartVo> buildTree(List<SPCChartVo> spcChartVos) {
        //按父节点分组
        Map<Long, List<SPCChartVo>> chartGroupByParent = spcChartVos.stream()
                .filter(spcChartVo -> spcChartVo.getParentId() != null)
                .collect(Collectors.groupingBy(SPCChartVo::getParentId));
        //子节点关联父节点
        spcChartVos.forEach(spcChartVo -> {
            List<SPCChartVo> children = chartGroupByParent.get(spcChartVo.getId());
            spcChartVo.setChildren(children);
        });
        //仅返回最上级
        return spcChartVos.stream().filter(spcChartVo -> spcChartVo.getParentId() == null).collect(Collectors.toList());
    }


}
