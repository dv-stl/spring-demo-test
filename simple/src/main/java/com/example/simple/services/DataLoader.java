package com.example.simple.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoader {

    public DataLoader() {
        System.out.println("I am DataLoader");
    }

    /**
     * This is meant to be a difficult operation to obtain the input data.
     */
    public List<Integer> loadData() {
        return List.of(
                Integer.valueOf(2),
                Integer.valueOf(3),
                Integer.valueOf(4),
                Integer.valueOf(5),
                Integer.valueOf(6),
                Integer.valueOf(7));
    }

}
