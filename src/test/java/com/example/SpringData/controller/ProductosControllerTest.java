package com.example.SpringData.controller;

import com.example.SpringData.model.request.ProductosRequest;
import com.example.SpringData.service.ProductosService;
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
public class ProductosControllerTest {

    @Qualifier("productosService")
    private final ProductosService productosService;

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductosControllerTest(ProductosService productosService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.productosService = productosService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    @DisplayName("ProductosControllerTest Test findByCodigo")
    void findByCodigo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/productos/{codigo}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("ProductosControllerTest Test findAll")
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/productos/{pageNo}/{pageSize}", 0, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    @DisplayName("ProductosControllerTest Test create")
    void create() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        ProductosRequest request = new ProductosRequest();
        request.setNombre("Avena");
        request.setPrecio(120);
        request.setIdVenta(idVentas);
        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    @DisplayName("ProductosControllerTest Test update")
    void update() throws Exception {
        Set<Integer> idVentas = new HashSet<>();
        idVentas.add(1);
        ProductosRequest request = new ProductosRequest();
        request.setNombre("Avena");
        request.setPrecio(120);
        request.setIdVenta(idVentas);
        mockMvc.perform(MockMvcRequestBuilders.put("/productos/{cajero}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(5)
    @DisplayName("ProductosControllerTest Test delete")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/productos/{cajero}", 2))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
