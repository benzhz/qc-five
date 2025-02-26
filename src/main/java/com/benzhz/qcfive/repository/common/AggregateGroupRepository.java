package com.benzhz.qcfive.repository.common;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

/**
 * MongoDB 通用聚合操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：AggregateGroupService
 * @Date：2025/2/16 22:15
 * @Filename：AggregateGroupService
 */
public interface AggregateGroupRepository {
    Object aggregationGroupCount(String collectionName, AggregationOperation group);

    Object aggregationGroupMax(String collectionName, AggregationOperation group);

    Object aggregationGroupMin(String collectionName, AggregationOperation group);

    Object aggregationGroupSum(String collectionName, AggregationOperation group);

    Object aggregationGroupAvg(String collectionName, AggregationOperation group);

    Object aggregationGroupFirst(String collectionName, AggregationOperation group, AggregationOperation sort);

    Object aggregationGroupLast(String collectionName, AggregationOperation group, AggregationOperation sort);

    Object aggregationGroupPush(String collectionName, AggregationOperation push);
}
