package com.benzhz.qcfive.repository.common.impl;

import com.benzhz.qcfive.repository.common.GrudSevice;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * mongdb 增删改查操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository.impl
 * @Project：qc-five
 * @name：GrudSeviceImpl
 * @Date：2025/2/16 22:15
 * @Filename：GrudSeviceImpl
 */
@Slf4j
@Service
public class GrudSeviceImpl implements GrudSevice {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 插入【一条】文档数据，如果文档信息已经【存在就抛出异常】
     *
     * @return 插入的文档信息
     */
    @Override
    public <T> T insert(T entity, String collectionName) {
        // 插入一条数据，如果文档信息已经存在就抛出异常
        T rsult = mongoTemplate.insert(entity, collectionName);
        // 输出存储结果
        log.info("存储的信息为：{}", rsult);
        return rsult;
    }

    /**
     * 插入【多条】文档数据，如果文档信息已经【存在就抛出异常】
     *
     * @return 插入的多个文档信息
     */
    @Override
    public <T> Collection<T> insertMany(List<T> list, String collectionName) {
        // 插入一条数据，如果某个文档信息已经存在就抛出异常
        Collection<T> results = mongoTemplate.insert(list, collectionName);
        // 输出存储结果
        for (T t : results) {
            log.info("存储的信息为：{}", t);
        }
        return results;
    }

    /**
     * 存储【一条】信息，如果文档信息已经【存在就执行更新】
     *
     * @return 存储的文档信息
     */
    @Override
    public <T> Object save(T entity, String collectionName) {

        // 存储信息,如果文档信息已经存在就执行更新
        T result = mongoTemplate.save(entity, collectionName);
        // 输出存储结果
        log.info("存储的信息为：{}", result);
        return result;
    }


    /**
     * 更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】
     *
     * @return 执行更新的结果
     */
    @Override
    public <T> Object update(String collectionName, Query query, Update update) {
        // 创建条件对象
        //  Criteria criteria = Criteria.where("age").is(30);
        // 创建查询对象，然后将条件对象添加到其中
        //  Query query = new Query(criteria);
        // 创建更新对象,并设置更新的内容
        //Update update = new Update().set("age", 33).set("name", "zhangsansan");
        // 执行更新，如果没有找到匹配查询的文档，则创建并插入一个新文档
        UpdateResult result = mongoTemplate.upsert(query, update, collectionName);
        // 输出结果信息
        String resultInfo = "匹配到" + result.getMatchedCount() + "条数据,对第一条数据进行了更改";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }

    /**
     * 更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】
     *
     * @return 执行更新的结果
     */
    @Override
    public Object updateFirst(String collectionName, Query query, Update update) {
        // 创建条件对象
        //  Criteria criteria = Criteria.where("name").is("zhangsan");
        // 创建查询对象，然后将条件对象添加到其中，并设置排序
        //Query query = new Query(criteria).with(Sort.by("age").ascending());
        // 创建更新对象,并设置更新的内容
        // Update update = new Update().set("age", 30).set("name", "zhangsansan");
        // 执行更新
        UpdateResult result = mongoTemplate.updateFirst(query, update, collectionName);
        // 输出结果信息
        String resultInfo = "共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }

    /**
     * 更新【匹配查询】到的【文档数据集合】中的【所有数据】
     *
     * @return 执行更新的结果
     */
    @Override
    public Object updateMany(String collectionName, Query query, Update update) {
        // 创建条件对象
        // Criteria criteria = Criteria.where("age").gt(28);
        // 创建查询对象，然后将条件对象添加到其中
        // Query query = new Query(criteria);
        // 设置更新字段和更新的内容
        //Update update = new Update().set("age", 29).set("salary", "1999");
        // 执行更新
        UpdateResult result = mongoTemplate.updateMulti(query, update, collectionName);
        // 输出结果信息
        String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }


    /**
     * 删除集合中【符合条件】的【一个]或[多个】文档
     *
     * @return 删除用户信息的结果
     */
    @Override
    public Object remove(String collectionName, Criteria criteria) {
        // 创建条件对象
        //  Criteria criteria = Criteria.where("age").is(age).and("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档信息
        DeleteResult result = mongoTemplate.remove(query, collectionName);
        // 输出结果信息
        String resultInfo = "成功删除 " + result.getDeletedCount() + " 条文档信息";
        log.info(resultInfo);
        return resultInfo;
    }

    /**
     * 删除【符合条件】的【单个文档】，并返回删除的文档。
     *
     * @return 删除的用户信息
     */
    @Override
    public <T> T findAndRemove(String collectionName, Query query, Class<T> entityClass) {
        // 创建条件对象
        //  Criteria criteria = Criteria.where("name").is(name);
        // 创建查询对象，然后将条件对象添加到其中
        //Query query = new Query(criteria);
        // 执行删除查找到的匹配的第一条文档,并返回删除的文档信息
        return mongoTemplate.findAndRemove(query, entityClass, collectionName);
    }

    /**
     * 删除【符合条件】的【全部文档】，并返回删除的文档。
     *
     * @return 删除的全部用户信息
     */
    @Override
    public <T> List<T> findAllAndRemove(String collectionName, Query query, Class<T> entityClass) {
        // 创建条件对象
        // Criteria criteria = Criteria.where("age").is(age);
        // 创建查询对象，然后将条件对象添加到其中
        //Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档,并返回删除的全部文档信息
        List<T> resultList = mongoTemplate.findAllAndRemove(query, entityClass, collectionName);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + resultList;
        log.info(resultInfo);
        return resultList;
    }


    /**
     * 查询集合中的【全部】文档数据
     *
     * @return 全部文档列表
     */
    @Override
    public <T> List<T> findAll(String collectionName, Class<T> entityClass) {
        // 执行查询集合中全部文档信息
        List<T> documentList = mongoTemplate.findAll(entityClass, collectionName);
        // 输出结果
        for (T documet : documentList) {
            log.info("信息：{}", documet);
        }
        return documentList;
    }

    /**
     * 根据【文档ID】查询集合中文档数据
     *
     * @return 文档信息
     */
    @Override
    public <T> Object findById(String collectionName, Class<T> entityClass, Object id) {
        // 根据文档ID查询集合中文档数据，并转换为对应 Java 对象
        T result = mongoTemplate.findById(id, entityClass, collectionName);
        // 输出结果
        log.info("信息：{}", result);
        return result;
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据
     *
     * @return 符合条件的第一条文档
     */
    @Override
    public <T> T findOne(String collectionName, Class<T> entityClass, Criteria criteria) {
        // 创建条件对象
        //Criteria criteria = Criteria.where("age").is(age);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 查询一条文档，如果查询结果中有多条文档，那么就取第一条
        T result = mongoTemplate.findOne(query, entityClass, collectionName);
        // 输出结果
        log.info("信息：{}", result);
        return result;
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> Object findByCondition(String collectionName, Class<T> entityClass, Criteria criteria) {
        // 创建条件对象
        //Criteria criteria = Criteria.where("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 查询并返回结果
        List<T> documentList = mongoTemplate.find(query, entityClass, collectionName);
        // 输出结果
        for (T document : documentList) {
            log.info("信息：{}", document);
        }
        return documentList;
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】并【排序】
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> List<T> findByConditionAndSort(String collectionName, Class<T> entityClass, Criteria criteria, String sort) {
        // 创建条件对象
        // Criteria criteria = Criteria.where("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中，然后根据指定字段进行排序
        Query query = new Query(criteria).with(Sort.by(sort));
        // 执行查询
        List<T> documentList = mongoTemplate.find(query, entityClass, collectionName);
        // 输出结果
        for (T document : documentList) {
            log.info("信息：{}", document);
        }
        return documentList;
    }

    /**
     * 根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【限制指定数目】
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> Object findByConditionAndSortLimit(String collectionName, Class<T> entityClass, Criteria criteria, String sort, int limit) {
        //    int limit = 2;
        // 创建条件对象
        //  Criteria criteria = Criteria.where("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria).with(Sort.by(sort)).limit(limit);
        // 执行查询
        List<T> documentList = mongoTemplate.find(query.limit(limit), entityClass, collectionName);
        // 输出结果
        for (T document : documentList) {
            log.info("信息：{}", document);
        }
        return documentList;
    }

    /**
     * 根据【单个条件】查询集合中的文档数据，并【按指定字段进行排序】与【并跳过指定数目】
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> List<T> findByConditionAndSortSkip(String collectionName, Class<T> entityClass, Criteria criteria, String sort, int skip) {
        // 创建条件对象
        //  Criteria criteria = Criteria.where("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria).with(Sort.by(sort)).skip(skip);
        // 查询并返回结果
        List<T> documentList = mongoTemplate.find(query.skip(skip), entityClass, collectionName);
        // 输出结果
        for (T document : documentList) {
            log.info("信息：{}", document);
        }
        return documentList;
    }

    /**
     * 查询【存在指定字段名称】的文档数据
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> List<T> findByExistsField(String collectionName, Class<T> entityClass, Query query) {
        // 创建条件
        // Criteria criteria = Criteria.where(field).exists(true);
        // 创建查询对象，然后将条件对象添加到其中
        //Query query = new Query(criteria);
        // 查询并返回结果
        List<T> documentList = mongoTemplate.find(query, entityClass, collectionName);
        // 输出结果
        for (T document : documentList) {
            log.info("信息：{}", document);
        }
        return documentList;
    }

    /**
     * 根据【AND】关联多个查询条件，查询集合中的文档数据
     *
     * @return 符合条件的文档列表
     */
    @Override
    public <T> Object findByAndCondition(String collectionName, Class<T> entityClass, Criteria... criteriaList) {
        // 创建条件
        //   Criteria criteriaSex = Criteria.where("sex").is(sex);
        // Criteria criteriaAge = Criteria.where("age").is(age);
        // 创建条件对象，将上面条件进行 AND 关联
        Criteria criteria = new Criteria().andOperator(criteriaList);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 查询并返回结果
        return mongoTemplate.find(query, entityClass, collectionName);
    }
}
