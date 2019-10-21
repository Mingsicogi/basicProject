package com.example.common.service.repository;

import com.example.common.vo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<Person, Integer> {
}
