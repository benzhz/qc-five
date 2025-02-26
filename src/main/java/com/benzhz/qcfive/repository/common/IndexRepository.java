package com.benzhz.qcfive.repository.common;

import org.bson.conversions.Bson;

/**
 * MongoDB 索引操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：IndexService
 * @Date：2025/2/16 22:15
 * @Filename：IndexService
 */
public interface IndexRepository {
    Object createAscendingIndex(String collectionName, String field);

    Object createDescendingIndex(String collectionName, String field);

    Object createCompositeIndex(String collectionName, String... fieldNames);

    Object createTextIndex(String collectionName, String field);

    Object createHashIndex(String collectionName, String field);

    Object createUniqueIndex(String collectionName, String indexName);

    Object createPartialIndex(String collectionName, String field, Bson filter);

    Object getIndexAll(String collectionName);

    void removeIndex(String collectionName, String indexName);

    void removeIndexAll(String collectionName);
}
