package com.mercan.mongo.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("flights")
@Builder
public class FlightInfo {
    @Id
    private String id;
    @Field(name = "departure")
    @Indexed
    @TextIndexed
    private String departureCity;

    @Field(name = "destination")
    @Indexed
    @TextIndexed
    private String destinationCity;
    @TextIndexed(weight =2)
    private String description;

    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private Aircraft aircraft;
    @Transient
    private LocalDate createdAt;
}
