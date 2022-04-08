package com.example.SpringData.controller;

import com.example.SpringData.model.request.ProductosRequest;
import com.example.SpringData.service.ProductosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping(value = "/productos")
@RestController()
public class ProductosController {

    @Qualifier("productosService")
    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody ProductosRequest request) throws SQLException {
        return new ResponseEntity<>(productosService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody ProductosRequest request,
                                         @PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(productosService.update(codigo, request), HttpStatus.OK);
    }

    @GetMapping(value = "/{pageNo}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@PathVariable("pageNo") Integer pageNo,
                                          @PathVariable("pageSize") Integer pageSize) {
        return new ResponseEntity<>(productosService.listAll(pageNo, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(productosService.delete(codigo), HttpStatus.NO_CONTENT);
    }

}