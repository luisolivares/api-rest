package com.example.SpringData.service.impl;

import com.example.SpringData.model.entities.Productos;
import com.example.SpringData.model.entities.Venta;
import com.example.SpringData.model.request.ProductosRequest;
import com.example.SpringData.repository.ProductosRepository;
import com.example.SpringData.repository.VentaRepository;
import com.example.SpringData.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("productosService")
public class ProductosServiceImpl implements ProductosService {

    @Qualifier("productosRepository")
    private final ProductosRepository productosRepository;

    @Qualifier("ventaRepository")
    private final VentaRepository ventaRepository;

    @Autowired
    public ProductosServiceImpl(ProductosRepository productosRepository, VentaRepository ventaRepository) {
        this.productosRepository = productosRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Productos create(ProductosRequest productosRequest) {
        Productos productos = new Productos();
        productos.setNombre(productosRequest.getNombre());
        List<Venta> venta = this.ventaRepository.listAllByCodigo(productosRequest.getIdVenta());
        productos.setVenta(venta);
        Productos result = this.productosRepository.save(productos);
        return result;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Productos update(Integer codigo, ProductosRequest productosRequest) {
        Productos result = new Productos();
        Optional<Productos> productos = this.productosRepository.findByCodigo(codigo);
        if (productos.isPresent()) {
            productos.get().setPrecio(productosRequest.getPrecio());
            productos.get().setNombre(productosRequest.getNombre());
            productos.get().setVenta(this.ventaRepository.listAllByCodigo(productosRequest.getIdVenta()));
            result = this.productosRepository.saveAndFlush(productos.get());
            return result;
        } else {
            return productos.get();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Productos findById(Integer codigo) {
        Optional<Productos> productos = this.productosRepository.findByCodigo(codigo);
        if(!productos.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Productos " + codigo + " not found");
        }

        return productos.get();


    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public boolean delete(Integer codigo) {
        Optional<Productos> cajero = this.productosRepository.findByCodigo(codigo);
        if (cajero.isPresent()) {
            this.productosRepository.delete(cajero.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Productos> listAll(Integer pageNo, Integer pageSize) {
        String sortBy = "codigo";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Productos> pagedResult = this.productosRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
