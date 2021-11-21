package com.accenture.assignment.airportsupport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Countries {
    private final String countryId;
    private final String countryCode;
    private final String countryName;
}
