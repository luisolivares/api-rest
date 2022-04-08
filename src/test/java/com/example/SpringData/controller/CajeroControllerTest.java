package com.example.SpringData.controller;

import com.example.SpringData.model.request.CajeroRequest;
import com.example.SpringData.service.CajeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        mockMvc.perform(MockMvcRequestBuilders.get("/cajeros/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("CajeroController Test findAll")
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cajeros/{pageNo}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("CajeroController Test create")
    void create() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        CajeroRequest request = new CajeroRequest();
        request.setNombre("Fulano");
        request.setIdVenta(idVentas);
        mockMvc.perform(post("/cajeros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    @DisplayName("CajeroController Test update")
    void update() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        CajeroRequest request = new CajeroRequest();
        request.setNombre("Fulano de tal");
        request.setIdVenta(idVentas);
        mockMvc.perform(MockMvcRequestBuilders.put("/cajeros/{cajero}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(5)
    @DisplayName("CajeroController Test delete")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cajeros/{cajero}", 2))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
