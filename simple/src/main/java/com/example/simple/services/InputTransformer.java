package com.example.simple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputTransformer {

    private final DataLoader loader;

    @Autowired
    public InputTransformer(final DataLoader loader) {
        this.loader = loader;
        System.out.println("I am InputTransformer");
    }

    /**
     * Imagine this is a very difficult transformation worth to thoughtful testing
     *
     * @return
     */
    public long transform() {
        return loader.loadData()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
