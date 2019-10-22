package com.example.common.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

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
    private String port;

    @Value("${data.mongodb.database}")
    private String dbName;

    @Value("${data.mongodb.username}")
    private String dbUser;

    @Value("${data.mongodb.password}")
    private char[] dbPassword;

    private static final String RDS_COMBINED_CA_BUNDLE = "rds-combined-ca-bundle.pem";

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(new ServerAddress(dbUri, Integer.parseInt(port)), mongoCredentials(), mongoClientOptions());
    }

    @Bean
    public MongoClientOptions mongoClientOptions() {
        MongoClientOptions.Builder mongoClientOptions = MongoClientOptions.builder().sslInvalidHostNameAllowed(true).sslEnabled(true);
        try {
            String fileName = "classpath:/" + RDS_COMBINED_CA_BUNDLE;
            InputStream is = new FileInputStream(fileName);
            // You could get a resource as a stream instead.

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate caCert = (X509Certificate) cf.generateCertificate(is);

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null); // You don't need the KeyStore instance to come from a file.
            ks.setCertificateEntry("caCert", caCert);

            tmf.init(ks);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            mongoClientOptions.sslContext(sslContext);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }


        return mongoClientOptions.build();
    }

    private MongoCredential mongoCredentials() {
        return MongoCredential.createCredential(dbUser, dbName, dbPassword);
    }
}
