package com.mercan.mongo.airport.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Airport {
    @Id
    private String id;
    private String country;
    private int nbF ;
}
