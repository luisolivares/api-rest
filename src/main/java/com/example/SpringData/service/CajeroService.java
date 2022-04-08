package com.example.SpringData.service;

import com.example.SpringData.model.entities.Cajero;
import com.example.SpringData.model.request.CajeroRequest;

import java.sql.SQLException;
import java.util.List;

public interface CajeroService {

    public Cajero create(CajeroRequest cajeroRequest) throws SQLException;

    public Cajero update(Integer codigo, CajeroRequest cajeroRequest);

    public Cajero findByCodigo(Integer codigo);

    public boolean delete(Integer id);

    public List<Cajero> listAll(Integer pageNo, Integer pageSize);

}
