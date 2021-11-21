package com.accenture.assignment.airportsupport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RunwayResponse {

    @JsonProperty("Airport Name")
    private String airportName;

    @JsonProperty("Airport Country")
    private String airportCountry;

    @JsonProperty("Number Of Runways")
    private int numberOfRunways;
}
