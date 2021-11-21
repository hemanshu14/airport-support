package com.accenture.assignment.airportsupport.utilities;

import java.util.ArrayList;
import java.util.List;

import com.accenture.assignment.airportsupport.model.Airports;
import com.accenture.assignment.airportsupport.model.Countries;
import com.accenture.assignment.airportsupport.model.Runways;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * CsvReader
 * <br>
 * <code>com.accenture.assignment.airportsupport.utilities.CsvReader</code>
 * <br>
 *
 * @author hemanshu.banga
 */

@Slf4j
@Getter
public class CsvReader extends AbstractCsvReader {
    public static final String AIRPORTS_FILE = "airports.csv";
    public static final String RUNWAYS_FILE = "runways.csv";
    public static final String COUNTRIES_FILE = "countries.csv";

    /**
     * Stores all parsed data from airports csv file
     * Once parsing is finished, these values should be used for anywhere required
     */
    private final List<Airports> airports = new ArrayList<>();

    /**
     * Stores all parsed data from runways csv file
     * Once parsing is finished, these values should be used for anywhere required
     */
    private final List<Runways> runways = new ArrayList<>();

    /**
     * Stores all parsed data from countries csv file
     * Once parsing is finished, these values should be used for anywhere required
     */
    private final List<Countries> countries = new ArrayList<>();

    /**
     * Parse a line of CSV data into required useful information
     *
     * @param line Line of CSV data
     */
    @Override
    protected void parseLine(String line, String fileName) {
        var row = line.split(",");

        if (!isValidFormat(row)) {
            return;
        }

        switch (fileName) {
        case AIRPORTS_FILE:
            airports.add(new Airports(parseStringInfo(row[0]), parseStringInfo(row[1]), parseStringInfo(row[3])));
            break;
        case RUNWAYS_FILE:
            runways.add(new Runways(parseStringInfo(row[0]), parseStringInfo(row[1])));
            break;
        case COUNTRIES_FILE:
            countries.add(new Countries(parseStringInfo(row[0]), parseStringInfo(row[1]), parseStringInfo(row[2])));
            break;
        default:
            break;
        }
    }

    /**
     * Parse a string into required information
     *
     * @param input String to parse
     * @return Parsed result
     */
    private String parseStringInfo(String input) {
        if (!input.isBlank()) {
            return input.trim();
        }
        return "";
    }

    /**
     * Validate whether the row matches the expected format
     *
     * @param row Row to validate
     * @return True if the row matches the expected format, false otherwise
     */
    private boolean isValidFormat(@NonNull final String[] row) {
        if (row.length == 0) {
            log.warn("the csv file contains an empty and / or unusable line");
            return false;
        }
        return true;
    }
}
