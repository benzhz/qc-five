package com.benzhz.qcfive.repository.impl;

import com.benzhz.qcfive.repository.SpcTableDataSourceRepository;
import com.benzhz.qcfive.repository.common.GrudSevice;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * spc控制图样本数据表操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository.impl
 * @Project：qc-five
 * @name：SpcTableColumnRepositoryImpl
 * @Date：2025/2/16 22:15
 * @Filename：SpcTableColumnRepositoryImpl
 */
@Repository
public class SpcTableDataSourceRepositoryImpl implements SpcTableDataSourceRepository {
    public static final String QC_SPC_ROW = "qc_spc_row";
    public static final String CHART_ID = "chartId";
    @Resource
    private GrudSevice grudSevice;

    /**
     * 批量插入样本数据
     * @param data
     */
    @Override
    public void batchSave(List<Map<String, String>> data) {
        grudSevice.insertMany(data, QC_SPC_ROW);
    }

    /**
     * 通过控制图id查询样本数据
     * @param chartId
     * @return
     */
    @Override
    public List<Map<String, String>> findByChartId(Long chartId) {
        Criteria criteria = Criteria.where(CHART_ID).is(chartId.toString());
        List<Map> maps = grudSevice.findByConditionAndSort(QC_SPC_ROW, Map.class, criteria, CHART_ID);
        return maps.stream().map(map -> {
            LinkedHashMap<String, String> result = new LinkedHashMap<>();
            for (Object key : map.keySet()) {
                result.put(key.toString(), map.get(key).toString());
            }
            return result;
        }).collect(Collectors.toList());
    }
    /**
     * 通过控制图id删除样本数据
     * @param chartId
     * @return
     */
    @Override
    public void deleteAllByChartId(Long chartId) {
        Criteria criteria = Criteria.where(CHART_ID).is(chartId.toString());
        grudSevice.remove(QC_SPC_ROW,criteria);
    }
}
