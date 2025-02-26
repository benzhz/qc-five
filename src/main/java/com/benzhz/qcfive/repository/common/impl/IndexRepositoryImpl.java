package com.benzhz.qcfive.repository.common.impl;

import com.benzhz.qcfive.repository.common.IndexRepository;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB 索引操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb.impl
 * @Project：qc-five
 * @name：IndexServiceImpl
 * @Date：2025/2/16 22:15
 * @Filename：IndexServiceImpl
 */
@Slf4j
@Service
public class IndexRepositoryImpl implements IndexRepository {
    /** 设置集合名称 */


    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建升序索引
     *
     * @return 索引信息
     */
    @Override
    public Object createAscendingIndex(String collectionName, String field) {
        // 设置字段名称
       // String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(field));
    }

    /**
     * 创建降序索引
     *
     * @return 索引信息
     */
    @Override
    public Object createDescendingIndex(String collectionName, String field) {
        // 设置字段名称
     //   String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.descending(field));
    }

    /**
     * 创建升序复合索引
     *
     * @return 索引信息
     */
    @Override
    public Object createCompositeIndex(String collectionName, String... fieldNames) {
        // 设置字段名称
//        String field1 = "name";
//        String field2 = "age";
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(fieldNames));
    }

    /**
     * 创建文字索引
     *
     * @return 索引信息
     */
    @Override
    public Object createTextIndex(String collectionName, String field) {
        // 设置字段名称
       // String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.text(field));
    }

    /**
     * 创建哈希索引
     *
     * @return 索引信息
     */
    @Override
    public Object createHashIndex(String collectionName, String field) {
        // 设置字段名称
       // String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.hashed(field));
    }

    /**
     * 创建升序唯一索引
     *
     * @return 索引信息
     */
    @Override
    public Object createUniqueIndex(String collectionName, String indexName) {
        // 设置字段名称
      //  String indexName = "name";
        // 配置索引选项
        IndexOptions options = new IndexOptions();
        // 设置为唯一索引
        options.unique(true);
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(indexName), options);
    }

    /**
     * 创建局部索引
     *
     * @return 索引信息
     */
    @Override
    public Object createPartialIndex(String collectionName, String field, Bson filter) {
        // 设置字段名称
        //String field = "name";
        //Filters.exists("name", true);
        // 配置索引选项
        IndexOptions options = new IndexOptions();
        // 设置过滤条件
        options.partialFilterExpression(filter);
        // 创建索引
        return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(field), options);
    }

    /**
     * 获取当前【集合】对应的【所有索引】的【名称列表】
     *
     * @return 当前【集合】所有【索引名称列表】
     */
    @Override
    public Object getIndexAll(String collectionName) {
        // 获取集合中所有列表
        ListIndexesIterable<Document> indexList = mongoTemplate.getCollection(collectionName).listIndexes();
        // 创建字符串集合
        List<Document> list = new ArrayList<>();
        // 获取集合中全部索引信息
        for (Document document : indexList) {
            log.info("索引列表：{}",document);
            list.add(document);
        }
        return list;
    }

    /**
     * 根据索引名称移除索引
     */
    @Override
    public void removeIndex(String collectionName, String indexName) {
        // 设置索引名称
   //     String indexName = "name_1";
        // 删除集合中某个索引
        mongoTemplate.getCollection(collectionName).dropIndex(indexName);
    }

    /**
     * 移除全部索引
     */
    @Override
    public void removeIndexAll(String collectionName) {
        // 删除集合中全部索引
        mongoTemplate.getCollection(collectionName).dropIndexes();
    }
}
