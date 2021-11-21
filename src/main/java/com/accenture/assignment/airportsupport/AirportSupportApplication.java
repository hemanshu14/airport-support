package com.accenture.assignment.airportsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.accenture.assignment.airportsupport.*" })
@OpenAPIDefinition( //
        info = @Info(title = "Airport Support Service", //
                description = "<p>Services which provide you the information of runways of each airport of a particular country</p>"))
public class AirportSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportSupportApplication.class, args);
    }

}
