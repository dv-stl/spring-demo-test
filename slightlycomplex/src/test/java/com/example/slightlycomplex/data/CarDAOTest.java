package com.example.slightlycomplex.data;

import com.example.slightlycomplex.domain.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan( basePackages = "com.example.slightlycomplex.data") // comment this out to see that only repositories are loaded
class CarDAOTest {

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private CarRepository carRepository;

    @DisplayName("Find all obtains all records from the DB and map these to domain object")
    @Test
    public void setUp() {
        // insert two objects to the BD
        var car1 = new Car("uuid1", "Fiat", 0.8, 2024);
        var car2 = new Car("uuid2", "Tesla", 6, 2024);

        carRepository.save(car1);
        carRepository.save(car2);

        // check if these can be fetched
        var listing = new ArrayList<>(carDAO.findAll());
        listing.sort(Comparator.comparing(Car::brand));

        assertThat(listing).hasSize(2);
        assertThat(listing).containsExactly(car1, car2);
    }
}