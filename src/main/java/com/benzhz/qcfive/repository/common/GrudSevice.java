package com.benzhz.qcfive.repository.common;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * mongdb 增删改查操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.repository
 * @Project：qc-five
 * @name：GrudSevice
 * @Date：2025/2/16 22:15
 * @Filename：GrudSevice
 */
public interface GrudSevice {
    <T> T insert(T entity, String collectionName);

    <T> Collection<T> insertMany(List<T> list, String collectionName);

    <T> Object save(T entity, String collectionName);

    <T> Object update(String collectionName, Query query, Update update);

    Object updateFirst(String collectionName, Query query, Update update);

    Object updateMany(String collectionName, Query query, Update update);

    Object remove(String collectionName, Criteria criteria);

    <T> T findAndRemove(String collectionName, Query query, Class<T> entityClass);

    <T> List<T> findAllAndRemove(String collectionName, Query query, Class<T> entityClass);

    <T> List<T> findAll(String collectionName, Class<T> entityClass);

    <T> Object findById(String collectionName, Class<T> entityClass, Object id);

    <T> T findOne(String collectionName, Class<T> entityClass, Criteria criteria);

    <T> Object findByCondition(String collectionName, Class<T> entityClass, Criteria criteria);

    <T> List<T> findByConditionAndSort(String collectionName, Class<T> entityClass, Criteria criteria, String sort);

    <T> Object findByConditionAndSortLimit(String collectionName, Class<T> entityClass, Criteria criteria, String sort, int limit);

    <T> List<T> findByConditionAndSortSkip(String collectionName, Class<T> entityClass, Criteria criteria, String sort, int skip);

    <T> List<T> findByExistsField(String collectionName, Class<T> entityClass, Query query);

    <T> Object findByAndCondition(String collectionName, Class<T> entityClass, Criteria... criteriaList);
}
