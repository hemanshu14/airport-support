package com.accenture.assignment.airportsupport.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * AbstractCsvReader
 * <br>
 * <code>com.accenture.assignment.airportsupport.utilities.AbstractCsvReader</code>
 * <br>
 *
 * @author hemanshu.banga
 */

@Slf4j
public abstract class AbstractCsvReader {
    /**
     * Load a CSV file from the "/resources" folder
     *
     * @param fileName   Name of the CSV file that needs to be loaded
     * @param skipHeader True to prevent the header line (first line) from being parsed at all
     */
    public void load(final @NonNull String fileName, final boolean skipHeader) {
        try {
            var inputStream = new ClassPathResource(fileName).getInputStream();
            var reader = new BufferedReader(new InputStreamReader(inputStream));
            var lines = reader.lines()
                              .collect(Collectors.toList());

            if (skipHeader && !lines.isEmpty()) {
                lines.remove(0);
            }

            lines.forEach(line -> parseLine(line, fileName));

            log.info("Finished parsing CSV {}", fileName);
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("Unable to find \"{}\"", fileName);
        } catch (IOException ioException) {
            log.error("Unable to open \"{}\"", fileName);
        }
    }

    /**
     * Parse a line of CSV data into usable information
     *
     * @param line Line of CSV data
     */
    protected abstract void parseLine(String line, String fileName);
}
