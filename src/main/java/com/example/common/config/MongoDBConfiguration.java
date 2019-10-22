package com.example.common.config;

import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 몽고 디비 클라이언트 객체를 빈으로 등록
 *
 * @author minssogi
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.example.*")
@Slf4j
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Value("${data.mongodb.host}")
    private String dbUri;

    @Value("${data.mongodb.port}")
    private int port;

    @Value("${data.mongodb.database}")
    private String dbName;

    @Value("${data.mongodb.username}")
    private String dbUser;

    @Value("${data.mongodb.password}")
    private char[] dbPassword;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(dbUri, port);
    }

    @Bean("minsDBTemplate")
    public MongoTemplate minsDBMongoTemplate(){
        return new MongoTemplate(mongoClient(), dbName);
    }
}
