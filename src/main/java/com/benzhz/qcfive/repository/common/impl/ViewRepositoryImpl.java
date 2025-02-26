package com.benzhz.qcfive.repository.common.impl;

import com.benzhz.qcfive.repository.common.ViewRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB 视图操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb.impl
 * @Project：qc-five
 * @name：ViewServiceImpl
 * @Date：2025/2/16 22:15
 * @Filename：ViewServiceImpl
 */
@Service
public class ViewRepositoryImpl implements ViewRepository {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建视图
     *
     * @return 创建视图结果
     */
    @Override
    public Object createView(String newViewName, String collectionName, Document match) {
       // Document match = Document.parse("{\"$match\":{\"sex\":\"女\"}}");
        // 定义视图的管道,可是设置视图显示的内容多个筛选条件
        List<Bson> pipeline = new ArrayList<>();
        // 设置条件，用于筛选集合中的文档数据，只有符合条件的才会映射到视图中
        pipeline.add(match);
        // 执行创建视图
        mongoTemplate.getDb().createView(newViewName, collectionName, pipeline);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(newViewName) ? "创建视图成功" : "创建视图失败";
    }

    /**
     * 删除视图
     *
     * @return 删除视图结果
     */
    @Override
    public Object dropView(String viewName) {
        // 检测视图是否存在
        if (mongoTemplate.collectionExists(viewName)) {
            // 删除视图
            mongoTemplate.getDb().getCollection(viewName).drop();
            return "删除视图成功";
        }
        // 检测新的集合是否存在，返回创建结果
        return !mongoTemplate.collectionExists(viewName) ? "删除视图成功" : "删除视图失败";
    }
}
