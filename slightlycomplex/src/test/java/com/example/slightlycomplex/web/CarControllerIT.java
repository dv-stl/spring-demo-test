package com.example.slightlycomplex.web;

import com.example.slightlycomplex.web.model.CarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void listCars() {
        String jsonCar1 = """
            {
                "brand": "Toyota",
                "engineCapacity": 1.8,
                "productionYear": 2022
            }
        """;
        String jsonCar2 = """
            {
                "brand": "Fiat",
                "engineCapacity": 0.8,
                "productionYear": 2006
            }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestCar1 = new HttpEntity<>(jsonCar1, headers);
        HttpEntity<String> requestCar2 = new HttpEntity<>(jsonCar2, headers);

        ResponseEntity<String> response1 = restTemplate.exchange(
                "/api/v1/cars",
                HttpMethod.POST,
                requestCar1,
                String.class
        );
        assertTrue(response1.getStatusCode().is2xxSuccessful());

        ResponseEntity<String> response2 = restTemplate.exchange(
                "/api/v1/cars",
                HttpMethod.POST,
                requestCar2,
                String.class
        );
        assertTrue(response2.getStatusCode().is2xxSuccessful());

        // cars created, let's fetch these
        ResponseEntity<List<CarDTO>> response = restTemplate.exchange(
                "/api/v1/cars",
                HttpMethod.GET,
                null, // No request body or headers
                new ParameterizedTypeReference<List<CarDTO>>() {}
        );

        List<CarDTO> listing = response.getBody();
        assertThat(listing).hasSize(2);
    }

}