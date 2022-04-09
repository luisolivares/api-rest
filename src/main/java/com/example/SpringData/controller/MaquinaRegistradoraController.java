package com.example.SpringData.controller;

import com.example.SpringData.model.request.MaquinaRegistradoraRequest;
import com.example.SpringData.service.MaquinaRegistradoraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping(value = "/maquinasRegistradoras")
@RestController()
public class MaquinaRegistradoraController {

    @Qualifier("maquinaRegistradoraService")
    private final MaquinaRegistradoraService maquinaRegistradoraService;

    @Autowired
    public MaquinaRegistradoraController(MaquinaRegistradoraService maquinaRegistradoraService) {
        this.maquinaRegistradoraService = maquinaRegistradoraService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody MaquinaRegistradoraRequest request) throws SQLException {
        return new ResponseEntity<>(maquinaRegistradoraService.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody MaquinaRegistradoraRequest request,
                                         @PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(maquinaRegistradoraService.update(codigo, request), HttpStatus.OK);
    }

    @GetMapping(value = "/{pageNo}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@PathVariable("pageNo") Integer pageNo,
                                          @PathVariable("pageSize") Integer pageSize) {
        return new ResponseEntity<>(maquinaRegistradoraService.listAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByCodigo(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(maquinaRegistradoraService.findByCodigo(codigo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable(value = "codigo", required = true) Integer codigo) throws SQLException {
        return new ResponseEntity<>(maquinaRegistradoraService.delete(codigo), HttpStatus.NO_CONTENT);
    }

}