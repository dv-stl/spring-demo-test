package com.example.slightlycomplex.domain.model;

import org.springframework.util.Assert;

public record Car (String uuid, String brand, double engineCapacity, int productionYear){

    public Car {
        Assert.hasLength(uuid, "Error");
    }
}
