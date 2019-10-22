package com.example.common.config;

import com.example.common.vo.Person;
import com.mongodb.client.result.DeleteResult;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class MongoDBConfigurationTest {

    @Autowired
    private MongoTemplate minsDBTemplate;

    @Test
    public void a_커넥션_확인(){
        Assert.assertTrue(minsDBTemplate.collectionExists("test"));
    }

    @Test
    public void b_데이터_삽입(){
        minsDBTemplate.insert(Person.builder().id(UUID.randomUUID().toString()).name("minssogi").age(20).build());
    }

    @Test
    public void c_데이터_수정(){
        Person dbInfo = minsDBTemplate.findOne(Query.query(Criteria.where("name").is("minssogi")), Person.class);

        dbInfo.setAge(290);

        minsDBTemplate.save(dbInfo);

        Person updateDbInfo = minsDBTemplate.findOne(Query.query(Criteria.where("age").is(290)), Person.class);
        Assert.assertNotNull(updateDbInfo);
    }

    @Test
    public void d_데이터_조회(){
        List<Person> dbInfo = minsDBTemplate.find(Query.query(Criteria.where("name").is("minssogi")), Person.class);

        Assert.assertNotNull(dbInfo);
    }

    @Test
    public void e_데이터_삭제(){
        DeleteResult result = minsDBTemplate.remove(Query.query(Criteria.where("name").is("minssogi")), Person.class);

        Assert.assertTrue("Success", result.getDeletedCount() >= 1);
    }
}