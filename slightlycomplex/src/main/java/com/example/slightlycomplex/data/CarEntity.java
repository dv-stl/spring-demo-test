package com.example.slightlycomplex.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CarEntity {

    @Id
    private String uuid;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private double engineCapacity;

    @Column(nullable = false)
    private int productionYear;
}
