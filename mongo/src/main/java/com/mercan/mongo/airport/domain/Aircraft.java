package com.mercan.mongo.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Getter
@Document
public class Aircraft {

    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private String family;
    private Engine engine;
    private int nbSeats;
}
