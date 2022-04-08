package com.example.SpringData.controller;

import com.example.SpringData.service.CajeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CajeroControllerTest {

    @Qualifier("cajeroService")
    private final CajeroService cajeroService;

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public CajeroControllerTest(CajeroService cajeroService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.cajeroService = cajeroService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    @DisplayName("CajeroController Test findByCodigo")
    void findByCodigo() throws Exception {
        mockMvc.perform(get("/cajeros/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("CajeroController Test findAll")
    void findAll() throws Exception {
        mockMvc.perform(get("/cajeros/{pageNo}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
