package com.example.SpringData.controller;

import com.example.SpringData.model.request.VentaRequest;
import com.example.SpringData.service.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping(value = "/ventas")
@RestController()
public class VentasController {

    @Qualifier("ventaService")
    private final VentaService ventaService;

    @Autowired
    public VentasController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody VentaRequest request) throws SQLException {
        return new ResponseEntity<>(ventaService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody VentaRequest request,
                                         @PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(ventaService.update(codigo, request), HttpStatus.OK);
    }

    @GetMapping(value = "/{pageNo}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@PathVariable("pageNo") Integer pageNo,
                                          @PathVariable("pageSize") Integer pageSize) {
        return new ResponseEntity<>(ventaService.listAll(pageNo, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(ventaService.delete(codigo), HttpStatus.NO_CONTENT);
    }

}