package com.example.slightlycomplex.web;

import com.example.slightlycomplex.domain.CarDealer;
import com.example.slightlycomplex.domain.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @MockBean
    private CarDealer carDealer;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Test the validation of the payload - payload is valid")
    @Test
    void testValidationOK() throws Exception {
        Car car =  new Car("uuid", "Toyota", 1.8, 2022);
        when(carDealer.addCarToTheListing(any())).thenReturn(car);

        String carJson = """
                    {
                        "brand": "Toyota",
                        "engineCapacity": 1.8,
                        "productionYear": 2022
                    }
                """;
        mockMvc.perform(post("/api/v1/cars")
                        .content(carJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @DisplayName("Test the validation of the payload - payload is invalid, errors are expected int he response")
    @Test
    void testValidation() throws Exception {
        String invalidCarJson = """
                    {
                        "brand": "",
                        "productionYear": 2022
                    }
                """;
        MvcResult res = mockMvc.perform(post("/api/v1/cars")
                        .content(invalidCarJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists())  // Ensure 'errors' field is present

                .andExpect(jsonPath("$.errors[0].field").value("brand"))
                .andExpect(jsonPath("$.errors[0].message").value("size must be between 2 and 30"))

                .andExpect(jsonPath("$.errors[1].field").value("engineCapacity"))
                .andExpect(jsonPath("$.errors[1].message").value("must not be null"))

                .andReturn();

        System.out.println(res.getResponse().getContentAsString());
    }

// TODO test with security

}