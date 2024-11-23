package com.example.simple.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple App is instantiated,
 * Main Spring App is not executed
 * Beans are created
 */
@SpringBootTest // by default loads whole app context, no main application.config is used
class InputTransformerTest {

    @Autowired
    private InputTransformer inputTransformer;

    @Test
    void transform() {
        assertThat(inputTransformer.transform()).isPositive();
    }
}