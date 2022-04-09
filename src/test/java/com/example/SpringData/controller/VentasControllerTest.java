package com.example.SpringData.controller;

import com.example.SpringData.model.request.VentaRequest;
import com.example.SpringData.service.VentaService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VentasControllerTest {

    @Qualifier("ventaService")
    private final VentaService ventaService;

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public VentasControllerTest(VentaService ventaService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.ventaService = ventaService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    @DisplayName("VentasControllerTest Test findByCodigo")
    void findByCodigo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ventas/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("VentasControllerTest Test findAll")
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ventas/{pageNo}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("VentasControllerTest Test create")
    void create() throws Exception {
        VentaRequest request = new VentaRequest();
        request.setIdCajero(1);
        request.setIdMaquinaRegistradora(1);
        request.setIdMaquinaRegistradora(1);
        mockMvc.perform(post("/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    @DisplayName("VentasControllerTest Test update")
    void update() throws Exception {
        VentaRequest request = new VentaRequest();
        request.setIdCajero(1);
        request.setIdMaquinaRegistradora(1);
        request.setIdMaquinaRegistradora(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/ventas/{cajero}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(5)
    @DisplayName("ProductosControllerTest Test delete")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ventas/{cajero}", 2))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
