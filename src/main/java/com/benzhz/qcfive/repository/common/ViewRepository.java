package com.benzhz.qcfive.repository.common;

import org.bson.Document;

/**
 * MongoDB 视图操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：ViewService
 * @Date：2025/2/16 22:15
 * @Filename：ViewService
 */
public interface ViewRepository {
    Object createView(String newViewName, String collectionName, Document match);

    Object dropView(String viewName);
}
