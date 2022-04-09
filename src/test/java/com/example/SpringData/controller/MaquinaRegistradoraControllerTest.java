package com.example.SpringData.controller;

import com.example.SpringData.model.request.MaquinaRegistradoraRequest;
import com.example.SpringData.service.MaquinaRegistradoraService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaquinaRegistradoraControllerTest {

    @Qualifier("maquinaRegistradoraService")
    private final MaquinaRegistradoraService maquinaRegistradoraService;

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public MaquinaRegistradoraControllerTest(MaquinaRegistradoraService maquinaRegistradoraService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.maquinaRegistradoraService = maquinaRegistradoraService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    @DisplayName("MaquinaRegistradoraControllerTest Test findByCodigo")
    void findByCodigo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maquinasRegistradoras/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("MaquinaRegistradoraControllerTest Test findAll")
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cajeros/{pageNo}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("MaquinaRegistradoraControllerTest Test create")
    void create() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        MaquinaRegistradoraRequest request = new MaquinaRegistradoraRequest();
        request.setPiso(6);
        request.setIdVenta(idVentas);
        mockMvc.perform(post("/maquinasRegistradoras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    @DisplayName("MaquinaRegistradoraControllerTest Test update")
    void update() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        MaquinaRegistradoraRequest request = new MaquinaRegistradoraRequest();
        request.setPiso(5);
        request.setIdVenta(idVentas);
        mockMvc.perform(MockMvcRequestBuilders.put("/maquinasRegistradoras/{cajero}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(5)
    @DisplayName("MaquinaRegistradoraControllerTest Test delete")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/maquinasRegistradoras/{cajero}", 2))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
