package com.accenture.assignment.airportsupport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public final class Airports {
    private final String airportId;
    private final String airportName;
    private final String airportCountry;
}
