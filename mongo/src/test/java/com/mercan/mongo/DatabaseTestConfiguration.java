package com.mercan.mongo;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@TestConfiguration
public class DatabaseTestConfiguration {

    @Bean
    MongoDbFactory mongoDbFactory(){
        return new SimpleMongoClientDbFactory("mongo://localhost:27017/db-tests");
    }
    @Bean
    MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory){
        return new MongoTemplate(mongoDbFactory);
    }


}
