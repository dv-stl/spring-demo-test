package com.example.slightlycomplex.web;

import com.example.slightlycomplex.app.SecurityConfig;
import com.example.slightlycomplex.domain.CarDealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@Import(SecurityConfig.class) //this is unfortunate, default security cuts off all access, so special import for config has to be made
class CarControllerSecurityTest {

    @MockBean
    private CarDealer carDealer;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test if only authenticated user can access price endpoint - 403 expected")
    void testAccessPriceUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/cars/price"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    @DisplayName("Test if only authenticated user can access price endpoint - OK")
    void testAccessPriceAuthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/cars/price"))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));
    }

}