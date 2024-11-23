package com.example.slightlycomplex.domain;

import com.example.slightlycomplex.data.CarDAO;
import com.example.slightlycomplex.domain.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CarDealer.class)
class CarDealerTest {

    @MockBean
    private CarDAO carDAO;

    @Autowired
    CarDealer carDealer;

    @DisplayName("Empty list is returned if there is not cars for sale")
            @Test
    void itWorksWhenEmpty() {
        when(carDAO.findAll()).thenReturn(List.of());
        List<Car> res = carDealer.getTheListing();

        // hamcrest
        assertThat(res, notNullValue());
        assertThat(res, empty());
    }
}