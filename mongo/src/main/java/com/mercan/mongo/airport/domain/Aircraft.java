package com.mercan.mongo.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Aircraft {
    private String model;
    private int nbSeats;
}
