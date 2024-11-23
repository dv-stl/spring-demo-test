package com.example.slightlycomplex.web.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCarCommand {

    @JsonCreator
    public CreateCarCommand(
            @JsonProperty("brand") String brand,
            @JsonProperty("engineCapacity") Double engineCapacity,
            @JsonProperty("productionYear") Integer productionYear) {
        this.brand = brand;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }

    @NotNull
    @Size(min = 2, max = 30)
    public final String brand;

    @NotNull
    @Positive
    public final Double engineCapacity;

    @NotNull
    @Min(2005)
    public final Integer productionYear;
}
