package com.mercan.mongo;

import com.mercan.mongo.airport.domain.FlightInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        FlightInfo flightInfo = FlightInfo.builder().departureCity("Sivas").build();
        this.mongoTemplate.save(flightInfo);
        Query destQuery = Query.query(Criteria.where("destination").is("Sivas"))
                .with(Sort.by(Sort.Direction.DESC, "destination"))
                .with(PageRequest.of(1, 10));
        List<FlightInfo> flightInfos = this.mongoTemplate.find(destQuery, FlightInfo.class);
        System.out.println("Count of flights: " + flightInfos.size());

        Query.query(Criteria.where("engine.needsMaintenance").is(true));
        System.out.println("Application is started ");
    }
}
