package com.accenture.assignment.airportsupport.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.assignment.airportsupport.model.CountriesResponse;
import com.accenture.assignment.airportsupport.model.RunwayResponse;
import com.accenture.assignment.airportsupport.service.AirportSupportService;

@ExtendWith(MockitoExtension.class)
class AirportControllerTest {

    @InjectMocks
    private AirportController airportController;

    @Mock
    private AirportSupportService airportSupportService;

    private static final RunwayResponse RUNWAY_RESPONSE = RunwayResponse.builder()
                                                                        .airportName("El Jagüel / Punta del Este Airport")
                                                                        .airportCountry("Uruguay")
                                                                        .numberOfRunways(1)
                                                                        .build();

    private static final List<RunwayResponse> RUNWAY_RESPONSE_LIST = Collections.singletonList(RUNWAY_RESPONSE);

    private static final CountriesResponse COUNTRIES_RESPONSE = CountriesResponse.builder()
                                                                                 .countryName("India")
                                                                                 .numberOfAirports(148L)
                                                                                 .build();

    private static final List<CountriesResponse> COUNTRIES_RESPONSE_LIST = Collections.singletonList(COUNTRIES_RESPONSE);

    @Test
    void calling_getRunwaysOverviewResponse_expect_response() {

        when(airportSupportService.getRunwaysOverview("UnitedKingdom")).thenReturn(RUNWAY_RESPONSE_LIST);

        final List<RunwayResponse> response = airportController.getRunwaysOverviewResponse("UnitedKingdom");

        assertThat(response).isNotNull();
        assertThat(response.get(0)
                           .getAirportCountry()).isEqualTo(RUNWAY_RESPONSE.getAirportCountry());
        verify(airportSupportService, times(1)).getRunwaysOverview("UnitedKingdom");
        verifyNoMoreInteractions(airportSupportService);
    }

    @Test
    void calling_getTopCountriesWithHighestAirports_expect_response() {

        when(airportSupportService.getCountriesWithHighestAirports()).thenReturn(COUNTRIES_RESPONSE_LIST);

        final List<CountriesResponse> response = airportController.getTopCountriesWithHighestAirports();

        assertThat(response).isNotNull();
        assertThat(response.get(0)
                           .getCountryName()).isEqualTo(COUNTRIES_RESPONSE.getCountryName());
        verify(airportSupportService, times(1)).getCountriesWithHighestAirports();
        verifyNoMoreInteractions(airportSupportService);
    }
}
