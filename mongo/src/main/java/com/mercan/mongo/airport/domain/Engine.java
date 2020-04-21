package com.mercan.mongo.airport.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Engine {
    private boolean needsMaintenance;
}
