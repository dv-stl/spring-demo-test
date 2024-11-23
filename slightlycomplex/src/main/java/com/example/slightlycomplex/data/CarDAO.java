package com.example.slightlycomplex.data;

import com.example.slightlycomplex.domain.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAO {
    private final CarRepository carRepository;

    @Autowired
    public CarDAO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(Car car) {
        CarEntity carEntity = new CarEntity();

        carEntity.setUuid(car.uuid());
        carEntity.setBrand(car.brand());
        carEntity.setProductionYear(car.productionYear());
        carEntity.setEngineCapacity(car.engineCapacity());

        carRepository.save(carEntity);
    }

    public List<Car> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::toDomainCarObject)
                .toList();
    }

    private Car toDomainCarObject(CarEntity ce) {
        return new Car(ce.getUuid(), ce.getBrand(), ce.getEngineCapacity(), ce.getProductionYear());
    }
}
