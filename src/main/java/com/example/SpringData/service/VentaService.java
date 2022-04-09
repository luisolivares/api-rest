package com.example.SpringData.service;

import com.example.SpringData.model.entities.Venta;
import com.example.SpringData.model.request.VentaRequest;

import java.util.List;

public interface VentaService {

    public Venta create(VentaRequest ventaRequest);

    public Venta update(Integer codigo, VentaRequest ventaRequest);

    public Venta findByCodigo(Integer codigo);

    public boolean delete(Integer codigo);

    public List<Venta> listAll(Integer pageNo, Integer pageSize);

}
