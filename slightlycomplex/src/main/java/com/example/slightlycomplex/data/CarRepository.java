package com.example.slightlycomplex.data;

import com.example.slightlycomplex.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, String> {

    default void save(Car car)  {
        CarEntity carEntity = new CarEntity();

        carEntity.setUuid(car.uuid());
        carEntity.setBrand(car.brand());
        carEntity.setProductionYear(car.productionYear());
        carEntity.setEngineCapacity(car.engineCapacity());

        save(carEntity);
    }
}
