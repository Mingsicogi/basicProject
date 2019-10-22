package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@PropertySource(value = {
        "classpath:common.properties"
}, encoding = "UTF-8")
@SpringBootApplication
@Slf4j
public class CommonApplication {

    public static void main(String[] args) {
//        SSLContextHelper.setSslProperties();
        SpringApplication.run(CommonApplication.class, args);
    }

//    protected static class SSLContextHelper {
//
//        private static final String DEFAULT_SSL_CERTIFICATE = "rds-ca-2015-us-east-1.pem";
//        private static final String SSL_CERTIFICATE = "sslCertificate";
//        private static final String KEY_STORE_TYPE = "JKS";
//        private static final String KEY_STORE_PROVIDER = "SUN";
//        private static final String KEY_STORE_FILE_PREFIX = "sys-connect-via-ssl-test-cacerts";
//        private static final String KEY_STORE_FILE_SUFFIX = ".jks";
//        private static final String DEFAULT_KEY_STORE_PASSWORD = "mins1234";
//        private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
//        private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
//        private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";
//
//
//
//
//        private static void setSslProperties() {
//
//            try {
//                String sslCertificate= System.getProperty(SSL_CERTIFICATE);
//                if(StringUtils.isEmpty(sslCertificate)) {
//                    sslCertificate= DEFAULT_SSL_CERTIFICATE;
//                }
//                log.info(" ssl certificate path {}",sslCertificate);
//                System.setProperty(SSL_TRUST_STORE, createKeyStoreFile(sslCertificate));
//                System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
//                System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        private static String createKeyStoreFile(String sslCertificate) throws Exception {
//            return createKeyStoreFile(createCertificate(sslCertificate)).getPath();
//        }
//
//        private static X509Certificate createCertificate(String sslCertificate) throws Exception {
//            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
//            URL url = new File(sslCertificate).toURI().toURL();
//            if (url == null) {
//                throw new Exception();
//            }
//            try (InputStream certInputStream = url.openStream()) {
//                return (X509Certificate) certFactory.generateCertificate(certInputStream);
//            }
//        }
//
//        private static File createKeyStoreFile(X509Certificate rootX509Certificate) throws Exception {
//            File keyStoreFile = File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX);
//            try (FileOutputStream fos = new FileOutputStream(keyStoreFile.getPath())) {
//                KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
//                ks.load(null);
//                ks.setCertificateEntry("rootCaCertificate", rootX509Certificate);
//                ks.store(fos, DEFAULT_KEY_STORE_PASSWORD.toCharArray());
//            }
//            return keyStoreFile;
//        }
//
//    }
}
