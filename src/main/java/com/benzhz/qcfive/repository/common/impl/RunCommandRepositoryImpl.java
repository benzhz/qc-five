package com.benzhz.qcfive.repository.common.impl;

import com.benzhz.qcfive.repository.common.RunCommandRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * MongoDB RunCommand 命令操作
 * @Author：zhz
 * @Package：com.benzhz.qcfive.service.mongodb
 * @Project：qc-five
 * @name：RunCommandServiceImpl
 * @Date：2025/2/16 22:15
 * @Filename：RunCommandServiceImpl
 */
@Slf4j
@Service
public class RunCommandRepositoryImpl implements RunCommandRepository {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 执行 mongoDB 自定义命令，详情可以查看：https://docs.mongodb.com/manual/reference/command/
     *
     * @return 执行命令返回结果的 Json 结果
     * @description 执行自定义 mongoDB 命令
     */
    @Override
    public Object runCommand(String jsonCommand) {
        // 自定义命令
        //   String jsonCommand = "{\"buildInfo\":1}";
        // 将 JSON 字符串解析成 MongoDB 命令
        Bson bson = Document.parse(jsonCommand);
        // 执行自定义命令
        return mongoTemplate.getDb().runCommand(bson);
    }

}
