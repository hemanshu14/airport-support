package com.accenture.assignment.airportsupport.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public final class Runways {
    private final String runwayId;
    private final String airportReferenceId;
}
