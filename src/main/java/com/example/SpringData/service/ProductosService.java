package com.example.SpringData.service;

import com.example.SpringData.model.entities.Productos;
import com.example.SpringData.model.request.ProductosRequest;

import java.util.List;

public interface ProductosService {

    public Productos create(ProductosRequest productosRequest);

    public Productos update(Integer id, ProductosRequest productosRequest);

    public Productos findById(Integer id);

    public boolean delete(Integer id);

    public List<Productos> listAll(Integer pageNo, Integer pageSize);

}
