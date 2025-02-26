package com.benzhz.qcfive.repository;

import java.util.List;
import java.util.Map;

/**
 * spc控制图样本数据表操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository
 * @Project：qc-five
 * @name：SpcTableDataSourceRepository
 * @Date：2025/2/16 22:15
 * @Filename：SpcTableDataSourceRepository
 */
public interface SpcTableDataSourceRepository {
    /**
     * 批量插入样本数据
     * @param data
     */
    void batchSave(List<Map<String, String>> data);
    /**
     * 通过控制图id查询样本数据
     * @param chartId
     * @return
     */
    List<Map<String, String>> findByChartId(Long chartId);

    /**
     * 通过控制图id删除样本数据
     * @param chartId
     * @return
     */
    void deleteAllByChartId(Long chartId);

}
