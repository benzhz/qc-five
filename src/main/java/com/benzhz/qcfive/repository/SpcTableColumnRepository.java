package com.benzhz.qcfive.repository;

import com.benzhz.qcfive.entity.SampleTableColumn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spc控制图样本数据表的表头信息操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository
 * @Project：qc-five
 * @name：SpcTableColumnRepository
 * @Date：2025/2/16 22:15
 * @Filename：SpcTableColumnRepository
 */
@Repository
public interface SpcTableColumnRepository extends MongoRepository<SampleTableColumn, Long> {
    /**
     * 通过控制图id查询，样本数据表头
     * @param chartId
     * @return
     */
    List<SampleTableColumn> findAllByChartId(Long chartId);
    /**
     * 通过控制图id删除，样本数据表头
     * @param chartId
     * @return
     */
    void deleteAllByChartId(Long chartId);
}
