package com.accenture.assignment.airportsupport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.assignment.airportsupport.model.Airports;
import com.accenture.assignment.airportsupport.model.Countries;
import com.accenture.assignment.airportsupport.model.Runways;
import com.accenture.assignment.airportsupport.utilities.CsvReader;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class ResourceLoadingService {
    private static ResourceLoadingService resourceLoadingService = new ResourceLoadingService();
    public static final String AIRPORTS_FILE = "airports.csv";
    public static final String RUNWAYS_FILE = "runways.csv";
    public static final String COUNTRIES_FILE = "countries.csv";
    private final List<Airports> airports;
    private final List<Runways> runways;
    private final List<Countries> countries;

    private ResourceLoadingService() {
        var csvReader = new CsvReader();
        csvReader.load(AIRPORTS_FILE, true);
        csvReader.load(RUNWAYS_FILE, true);
        csvReader.load(COUNTRIES_FILE, true);
        airports = csvReader.getAirports();
        runways = csvReader.getRunways();
        countries = csvReader.getCountries();
    }

    public static ResourceLoadingService getInstance() {
        return resourceLoadingService;
    }
}
