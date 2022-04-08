package com.example.SpringData.controller;

import com.example.SpringData.model.request.CajeroRequest;
import com.example.SpringData.service.CajeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping(value = "/cajeros")
@RestController()
public class CajeroController {

    @Qualifier("cajeroService")
    private final CajeroService cajeroService;

    @Autowired
    public CajeroController(CajeroService cajeroService) {
        this.cajeroService = cajeroService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody CajeroRequest request) throws SQLException {
        return new ResponseEntity<>(cajeroService.create(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByCodigo(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(cajeroService.findByCodigo(codigo), HttpStatus.OK);
    }

    @PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody(required = true) CajeroRequest request,
                                         @PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(cajeroService.update(codigo, request), HttpStatus.OK);
    }

    @GetMapping(value = "/{pageNo}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@PathVariable("pageNo") Integer pageNo,
                                          @PathVariable("pageSize") Integer pageSize) {
        return new ResponseEntity<>(cajeroService.listAll(pageNo, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(cajeroService.delete(codigo), HttpStatus.NO_CONTENT);
    }

}