package com.benzhz.qcfive.repository.common;

import org.springframework.data.mongodb.core.query.CriteriaDefinition;

/**
 * MongoDB 集合操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：CollectionService
 * @Date：2025/2/16 22:15
 * @Filename：CollectionService
 */
public interface CollectionRepository {
    Object createCollection(String collectionName);

    Object createCollectionFixedSize(String collectionName, long size, long max);

    Object createCollectionValidation(String collectionName, CriteriaDefinition criteria);

    Object getCollectionNames();

    boolean collectionExists(String collectionName);

    Object dropCollection(String collectionName);
}
