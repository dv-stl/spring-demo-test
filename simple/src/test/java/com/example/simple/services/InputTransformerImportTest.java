package com.example.simple.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Import tested bean to ensure necessary beans are created
 */
@SpringBootTest(classes = {InputTransformer.class}) // Only load InputTransformer
class InputTransformerImportTest {

    @MockBean
    private DataLoader dataLoader;

    @Autowired
    private InputTransformer inputTransformer;

    @Test
    void transform() {
        when(dataLoader.loadData()).thenReturn(List.of(2, 4));
        assertThat(inputTransformer.transform()).isPositive();
    }
}