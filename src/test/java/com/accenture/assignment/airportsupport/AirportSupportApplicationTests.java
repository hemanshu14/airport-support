package com.accenture.assignment.airportsupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.accenture.assignment.airportsupport.controller.AirportController;
import com.accenture.assignment.airportsupport.service.AirportSupportService;

@SpringBootTest
class AirportSupportApplicationTests {

    @Autowired
    private AirportController airportController;
    @Autowired
    private AirportSupportService airportSupportService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(airportController);
        Assertions.assertNotNull(airportSupportService);
    }

}
