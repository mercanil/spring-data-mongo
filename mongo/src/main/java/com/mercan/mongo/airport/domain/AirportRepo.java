package com.mercan.mongo.airport.domain;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepo extends MongoRepository<Airport, String> {
    List<Airport> findByFlightsPerDayGreaterThan(int value);
}
