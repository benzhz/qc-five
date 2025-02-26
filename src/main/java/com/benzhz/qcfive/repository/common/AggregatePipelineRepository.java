package com.benzhz.qcfive.repository.common;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

/**
 * MongoDB 聚合管道操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：AggregatePipelineService
 * @Date：2025/2/16 22:15
 * @Filename：AggregatePipelineService
 */
public interface AggregatePipelineRepository {
    Object aggregateGroupMatch(String collectionName, AggregationOperation match, AggregationOperation group);

    Object aggregateGroupSort(String collectionName, AggregationOperation group, AggregationOperation sort);

    Object aggregateGroupLimit(String collectionName, AggregationOperation group, AggregationOperation limit);

    Object aggregateGroupSkip(String collectionName, AggregationOperation group, AggregationOperation limit);

    Object aggregateGroupProject(String collectionName, AggregationOperation group, AggregationOperation project);

    Object aggregateProjectUnwind(String collectionName, AggregationOperation project, AggregationOperation unwind);
}
