package com.accenture.assignment.airportsupport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountriesResponse {

    @JsonProperty("Country Name")
    private String countryName;

    @JsonProperty("Number Of Airports")
    private Long numberOfAirports;
}
