package com.benzhz.qcfive.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * MongoDB 配置事务管理器
 * @Author：zhz
 * @Package：com.benzhz.qcfive.config
 * @Project：qc-five
 * @name：TransactionConfig
 * @Date：2025/2/16 22:15
 * @Filename：TransactionConfig
 */
@Configuration
public class TransactionConfig {

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
