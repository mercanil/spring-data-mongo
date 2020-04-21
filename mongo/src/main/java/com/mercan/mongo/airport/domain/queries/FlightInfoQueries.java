package com.mercan.mongo.airport.domain.queries;


import com.mercan.mongo.airport.domain.FlightInfo;
import com.mercan.mongo.airport.domain.FlightType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class FlightInfoQueries {

    private final MongoTemplate mongoTemplate;

    public List<FlightInfo> findAll(String field, int pageNb, int pageSize) {
        Query allPagedAndOrdered = new Query().with(Sort.by(Sort.Direction.ASC, field));
        return this.mongoTemplate.find(allPagedAndOrdered, FlightInfo.class);
    }

    public FlightInfo findSingleById(String id) {
        Query queryById = Query.query(where("id").is(id));
        return this.mongoTemplate.findOne(queryById, FlightInfo.class);
        // or this.mongoTemplate.findById(id, FlightInfo.class);
    }

    public List<FlightInfo> findInternational() {
        Query internationFlights = Query.query(where("type").is(FlightType.INTERNATIONAL));
        return this.mongoTemplate.find(internationFlights, FlightInfo.class);
    }

    public List<FlightInfo> findByDeparture(String departure) {
        Query departureQuery = Query.query(where("departure").is(departure));
        return this.mongoTemplate.find(departureQuery, FlightInfo.class);
    }

    public List<FlightInfo> findByDuration(int minMinute, int maxMinute) {
        Query departureQuery = Query.query(where("durationMin").gt(minMinute).lt(maxMinute))
                .with(Sort.by(Sort.Direction.DESC, "durationMin"));
        return this.mongoTemplate.find(departureQuery, FlightInfo.class);
    }

    public List<FlightInfo> findFlightRelatedToCity(String city) {
        Criteria destOrDepartCity = new Criteria().orOperator(where("departure").is(city)
                , where("destination").is(city));
        Criteria isDelayed = where("isDelayed").is(false);
        Query relatedToCityWithoutDelayed = Query.query(destOrDepartCity.andOperator(isDelayed));
        return this.mongoTemplate.find(relatedToCityWithoutDelayed, FlightInfo.class);
    }

    public List<FlightInfo> findByAircraftModel(String model) {
        Query queryBtModel = Query.query(where("aircraft.model").is(model));
        return this.mongoTemplate.find(queryBtModel, FlightInfo.class);
    }

    public List<FlightInfo> findByTextSearch(String text) {

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);
        Query query = TextQuery.queryText(textCriteria).sortByScore().with(PageRequest.of(0, 3));
        return this.mongoTemplate.find(query, FlightInfo.class);


    }
}
