package com.example.common.config;

import com.example.common.service.repository.PersonMongoRepository;
import com.example.common.vo.Person;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class MongoDBConfigurationTest {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private PersonMongoRepository personMongoRepository;


    @Autowired
    private MongoDBConfiguration mongoDBConfiguration;

    @Test
    public void a_커넥션_확인(){
//        String template = "mongodb://%s:%s@%s/?ssl=true&replicaSet=rs0&readpreference=%s";
//        String username = "minsMongoDB2";
//        String password = "test1234";
//        String clusterEndpoint = "docdb-2019-10-20-11-14-37.cluster-cpfhuqglxhri.ap-northeast-2.docdb.amazonaws.com";
//        String readPreference = "secondaryPreferred";
//        String connectionString = String.format(template, username, password, clusterEndpoint, readPreference);
//
//        String keystore = "rds-ca-certs";
//        String keystorePassword = "mins1234";
//
//        System.setProperty("javax.net.ssl.trustStore", keystore);
//        System.setProperty("javax.net.ssl.trustStorePassword", keystorePassword);
//
//        MongoClientURI clientURI = new MongoClientURI(connectionString);
//        MongoClient mongoClient = new MongoClient(clientURI);
//
//        MongoDatabase testDB = mongoClient.getDatabase("admin");
//        MongoCollection<Document> numbersCollection = testDB.getCollection("test");
//
//        Document doc = new Document("name", "pi").append("value", 3.14159);
//        numbersCollection.insertOne(doc);
//
//        MongoCursor<Document> cursor = numbersCollection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }

        MongoDatabase testDB = mongoDBConfiguration.mongoClient().getDatabase("admin");
        MongoCollection<Document> numbersCollection = testDB.getCollection("test");

        Document doc = new Document("name", "pi").append("value", 3.14159);
        numbersCollection.insertOne(doc);

        MongoCursor<Document> cursor = numbersCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

    @Test
    public void b_데이터_삽입(){
//        MongoDatabase database = mongoClient.getDatabase("admin");
//        MongoCollection mongoCollection = database.getCollection("test");

        personMongoRepository.insert(Person.builder().id(1).name("minssogi").age(29).build());
    }

    @Test
    public void c_데이터_수정(){

    }

    @Test
    public void d_데이터_조회(){

    }

    @Test
    public void e_데이터_삭제(){

    }
}