package com.mercan.mongo;


import com.mercan.mongo.airport.domain.Airport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(DatabaseTestConfiguration.class)
public class DatabaseIntegrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;


    @BeforeEach
    public void beforeEach() {
        this.mongoTemplate.insertAll(Arrays.asList(new Airport()));
    }

    @AfterEach
    public void afterEach() {
        this.mongoTemplate.dropCollection(Airport.class);
    }

    @Test
    public void count_should_be_3() {
        long count = this.mongoTemplate.count(new Query(), Airport.class);
        assertEquals(3, count);
    }
}
