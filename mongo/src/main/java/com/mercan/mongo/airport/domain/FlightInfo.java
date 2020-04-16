package com.mercan.mongo.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Document("flights")
public class FlightInfo {
    @Id
    private String id;
    @Field(name = "departure")
    @Indexed
    private String departureCity;
    @Field(name = "destination")
    @Indexed
    private String destinationCity;
    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private Aircraft aircraft;
    @Transient
    private LocalDate createdAt;
}
