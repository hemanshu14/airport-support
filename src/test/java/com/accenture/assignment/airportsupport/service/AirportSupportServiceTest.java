package com.accenture.assignment.airportsupport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.assignment.airportsupport.exception.DataNotFoundException;
import com.accenture.assignment.airportsupport.model.CountriesResponse;
import com.accenture.assignment.airportsupport.model.RunwayResponse;

@ExtendWith(MockitoExtension.class)
class AirportSupportServiceTest {

    @InjectMocks
    private AirportSupportService airportSupportService;

    @Test
    void when_getRunwaysOverview_called_with_correct_parameters_expect_response() {
        final List<RunwayResponse> response = airportSupportService.getRunwaysOverview("Paraguay");

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(9);
    }

    @Test
    void when_getRunwaysOverview_called_with_incorrect_parameters_expect_error_response() {
        assertThrows(DataNotFoundException.class, () -> airportSupportService.getRunwaysOverview("Amazon"));
    }

    @Test
    void when_getRunwaysOverview_called_with_incorrect_country_code_expect_error_response() {
        assertThrows(DataNotFoundException.class, () -> airportSupportService.getRunwaysOverview("UK"));
    }

    @Test
    void when_getCountriesWithHighestAirports_called_with_correct_parameters_expect_response() {
        final List<CountriesResponse> response = airportSupportService.getCountriesWithHighestAirports();

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(10);
    }
}
