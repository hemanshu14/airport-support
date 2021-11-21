package com.accenture.assignment.airportsupport.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.assignment.airportsupport.model.CountriesResponse;
import com.accenture.assignment.airportsupport.model.RunwayResponse;
import com.accenture.assignment.airportsupport.service.AirportSupportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * AirportController
 * <br>
 * <code>com.accenture.assignment.airportsupport.controller.AirportController</code>
 * <br>
 *
 * @author hemanshu.banga
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/airport-support")
public class AirportController {

    private final AirportSupportService airportSupportService;

    /**
     * Method to retrieve the runway details for each airport from the service with the provided country code or country name
     *
     * @param countryInfo required country name or country code
     * @return {@link List<RunwayResponse>} list of the runway details for each airport of the asked country
     */
    @GetMapping("/runways/overview/country/{countryInfo}")
    @ResponseBody
    @Operation(summary = "Receive the runways information of an airport given a country code or country name", //
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved runways details", //
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RunwayResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json")) })
    public List<RunwayResponse> getRunwaysOverviewResponse(//
            @Parameter(name = "countryInfo", required = true, description = "The country code or country name") @Valid @NonNull @PathVariable final String countryInfo) {
        return airportSupportService.getRunwaysOverview(countryInfo);
    }

    /**
     * Method to retrieve top 10 countries with highest number of airports
     *
     * @return {@link List<CountriesResponse>} list of top 10 countries with highest number of airports
     */
    @GetMapping("/countries/top/10")
    @ResponseBody
    @Operation(summary = "Receive the top 10 countries with highest number of airports", //
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved 10 countries with highest number of airports", //
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CountriesResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json")) })
    public List<CountriesResponse> getTopCountriesWithHighestAirports() {
        return airportSupportService.getCountriesWithHighestAirports();
    }
}
