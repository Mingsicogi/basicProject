package com.example.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 몽고 디비 클라이언트 객체를 빈으로 등록
 *
 * @author minssogi
 */
@Configuration
public class MongoDBConfiguration {

    @Value("${javax.net.ssl.trustStore}")
    private String keystore;

    @Value("${javax.net.ssl.trustStorePassword}")
    private String keystorePassword;

    @PostConstruct
    public void init(){
        System.setProperty("javax.net.ssl.trustStore", keystore);
        System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);
    }
}
