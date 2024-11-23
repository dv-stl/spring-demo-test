package com.example.slightlycomplex.domain;

import com.example.slightlycomplex.data.CarDAO;
import com.example.slightlycomplex.domain.model.Car;
import com.example.slightlycomplex.web.command.CreateCarCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarDealer {

    private final CarDAO carDAO;

    @Autowired
    public CarDealer(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car addCarToTheListing(CreateCarCommand cmd) {
        Car car = new Car(
                UUID.randomUUID().toString(),
                cmd.brand,
                cmd.engineCapacity,
                cmd.productionYear
        );

        carDAO.save(car);
        return car;
    }

    public List<Car> getTheListing() {
        return carDAO.findAll();
    }
}
