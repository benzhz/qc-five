package com.benzhz.qcfive.repository;

import com.benzhz.qcfive.entity.SpcChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spc控制图信息操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository.spcchart
 * @Project：qc-five
 * @name：SpcChartRepository
 * @Date：2025/2/16 22:15
 * @Filename：SpcChartRepository
 */
@Repository
public interface SpcChartRepository extends MongoRepository<SpcChart, Long> {

    /**
     * 通过控制图id，查询控制图
     * @param id
     * @return
     */
    SpcChart getById(Long id);

    /**
     * 通过控制图名称，模糊查询控制图
     * @param name
     * @return
     */
    List<SpcChart> findByNameLike(String name);
}
