package com.accenture.assignment.airportsupport.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.accenture.assignment.airportsupport.exception.DataNotFoundException;
import com.accenture.assignment.airportsupport.model.Airports;
import com.accenture.assignment.airportsupport.model.Countries;
import com.accenture.assignment.airportsupport.model.CountriesResponse;
import com.accenture.assignment.airportsupport.model.RunwayResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * AirportSupportService
 * <br>
 * <code>com.accenture.assignment.airportsupport.service.AirportSupportService</code>
 * <br>
 *
 * @author hemanshu.banga
 */

@Slf4j
@Service
public class AirportSupportService {

    /**
     * Method to retrieve the runway details for each airport from the data retrieved from the csv files
     * with the provided country code or country name
     *
     * @param countryInfo required country name or country code
     * @return {@link List<RunwayResponse>} list of the runway details for each airport of the asked country
     */
    public List<RunwayResponse> getRunwaysOverview(String countryInfo) {

        var airports = ResourceLoadingService.getInstance().getAirports();

        var runwaysResponse = new ArrayList<RunwayResponse>();

        var runways = ResourceLoadingService.getInstance()
                                            .getRunways();

        var countryName = countryInfo.length() == 2 ? getCountryName(countryInfo) : countryInfo.replaceAll("\\s", "");

        var airportsMap = airports.stream()
                                  .filter(a -> a.getAirportCountry()
                                                .replaceAll("\\s", "")
                                                .equalsIgnoreCase(countryName) || a.getAirportCountry()
                                                                                   .replaceAll("\\s", "")
                                                                                   .toLowerCase()
                                                                                   .contains(countryName.toLowerCase()))
                                  .collect(Collectors.toMap(Airports::getAirportId, entry -> entry));

        if (airportsMap.isEmpty()) {
            throw new DataNotFoundException("No airports data found for this country");
        }

        airportsMap.forEach((airportId, airport) -> {
            var count = runways.stream()
                               .filter(r -> r.getAirportReferenceId()
                                             .equalsIgnoreCase(airportId))
                               .count();
            RunwayResponse runwayResponse = RunwayResponse.builder()
                                                          .airportName(airport.getAirportName())
                                                          .numberOfRunways((int) count)
                                                          .airportCountry(airport.getAirportCountry())
                                                          .build();
            runwaysResponse.add(runwayResponse);
        });

        return runwaysResponse;
    }

    /**
     * Method to retrieve the country name based on the given country code
     *
     * @param countryCode country code
     * @return {@link List<RunwayResponse>} list of the runway details for each airport of the asked country
     */
    private String getCountryName(String countryCode) {
        var countries = ResourceLoadingService.getInstance()
                                              .getCountries();
        return countries.stream()
                        .filter(c -> c.getCountryCode()
                                      .equalsIgnoreCase(countryCode))
                        .findFirst()
                        .map(Countries::getCountryName)
                        .orElseThrow(() -> new DataNotFoundException("No country found for this country code in the csv file"))
                        .replaceAll("\\s", "");
    }

    /**
     * Method to retrieve top 10 countries with highest number of airports
     *
     * @return {@link List<CountriesResponse>} list of top 10 countries with highest number of airports
     */
    public List<CountriesResponse> getCountriesWithHighestAirports() {
        var airports = ResourceLoadingService.getInstance()
                                             .getAirports();

        var countriesResponseList = new ArrayList<CountriesResponse>();

        var reverseSortedMap = new LinkedHashMap<String, Long>();

        var airportsGroupedMap = airports.stream()
                                         .collect(Collectors.groupingBy(Airports::getAirportCountry, Collectors.counting()));

        if (!airportsGroupedMap.isEmpty()) {
            airportsGroupedMap.entrySet()
                              .stream()
                              .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                              .limit(10)
                              .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

            reverseSortedMap.forEach((country, numberOfAirports) -> {
                CountriesResponse countriesResponse = CountriesResponse.builder()
                                                                       .countryName(country)
                                                                       .numberOfAirports(numberOfAirports)
                                                                       .build();
                countriesResponseList.add(countriesResponse);
            });
        } else {
            throw new DataNotFoundException("No airports data found!!");
        }

        return countriesResponseList;
    }
}
