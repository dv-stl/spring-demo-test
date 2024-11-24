package com.example.slightlycomplex.web;

import com.example.slightlycomplex.domain.CarDealer;
import com.example.slightlycomplex.domain.model.Car;
import com.example.slightlycomplex.web.command.CreateCarCommand;
import com.example.slightlycomplex.web.model.CarDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarDealer carDealer;

    public CarController(CarDealer carDealer) {
        this.carDealer = carDealer;
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> listCars() {
        List<Car> cars = carDealer.getTheListing();

        var dtos = cars.stream()
                .map(this::toViewDTO)
                .toList();


        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody @Valid CreateCarCommand carCommand) {
        Car freshlyAddedCar = carDealer.addCarToTheListing(carCommand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(freshlyAddedCar.uuid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/price")
    public String getPrice() {
        return "42";
    }

    private CarDTO toViewDTO(Car car) {
        var dto = new CarDTO();
        dto.uuid = car.uuid();
        dto.brand = car.brand();
        dto.engineCapacity = car.engineCapacity();
        dto.productionYear = car.productionYear();

        return dto;
    }
}