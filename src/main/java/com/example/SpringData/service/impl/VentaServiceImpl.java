package com.example.SpringData.service.impl;

import com.example.SpringData.model.entities.Cajero;
import com.example.SpringData.model.entities.MaquinaRegistradora;
import com.example.SpringData.model.entities.Productos;
import com.example.SpringData.model.entities.Venta;
import com.example.SpringData.model.request.VentaRequest;
import com.example.SpringData.repository.CajeroRepository;
import com.example.SpringData.repository.MaquinaRegistradoraRepository;
import com.example.SpringData.repository.ProductosRepository;
import com.example.SpringData.repository.VentaRepository;
import com.example.SpringData.service.VentaService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service("ventaService")
public class VentaServiceImpl implements VentaService {

    @Qualifier("cajeroRepository")
    private final CajeroRepository cajeroRepository;

    @Qualifier("productosRepository")
    private final ProductosRepository productosRepository;

    @Qualifier("maquinaRegistradoraRepository")
    private final MaquinaRegistradoraRepository maquinaRegistradoraRepository;

    @Qualifier("ventaRepository")
    private VentaRepository ventaRepository;

    @Autowired
    public VentaServiceImpl(CajeroRepository cajeroRepository, ProductosRepository productosRepository, MaquinaRegistradoraRepository maquinaRegistradoraRepository, VentaRepository ventaRepository) {
        this.cajeroRepository = cajeroRepository;
        this.productosRepository = productosRepository;
        this.maquinaRegistradoraRepository = maquinaRegistradoraRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Venta create(VentaRequest ventaRequest) {
        Venta venta = new Venta();
        Optional<Cajero> cajero = this.cajeroRepository.findByCodigo(ventaRequest.getIdCajero());
        Optional<Productos> productos = this.productosRepository.findByCodigo(ventaRequest.getIdProductos());
        Optional<MaquinaRegistradora> maquinaRegistradora = this.maquinaRegistradoraRepository.findByCodigo(ventaRequest.getIdMaquinaRegistradora());
        if (cajero.isPresent() && productos.isPresent() && maquinaRegistradora.isPresent()) {
            venta.setCajero(cajero.get());
            venta.setProductos(productos.get());
            venta.setMaquinaRegistradora(maquinaRegistradora.get());
            venta = this.ventaRepository.save(venta);
        }
        return venta;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Venta update(Integer codigo, VentaRequest ventaRequest) {
        Venta result = new Venta();
        Optional<Venta> venta = this.ventaRepository.findByCodigo(codigo);
        Optional<Cajero> cajero = this.cajeroRepository.findByCodigo(ventaRequest.getIdCajero());
        Optional<Productos> productos = this.productosRepository.findByCodigo(ventaRequest.getIdProductos());
        Optional<MaquinaRegistradora> maquinaRegistradora = this.maquinaRegistradoraRepository.findByCodigo(ventaRequest.getIdMaquinaRegistradora());
        if (venta.isPresent() && cajero.isPresent() && productos.isPresent() && maquinaRegistradora.isPresent()) {
            venta.get().setCajero(cajero.get());
            venta.get().setProductos(productos.get());
            venta.get().setMaquinaRegistradora(maquinaRegistradora.get());
            result = this.ventaRepository.saveAndFlush(venta.get());
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Venta findById(Integer codigo) {
        Optional<Venta> venta = this.ventaRepository.findByCodigo(codigo);
        if(!venta.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta " + codigo + " not found");
        }
        return venta.get();
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public boolean delete(Integer codigo) {
        Optional<Venta> venta = this.ventaRepository.findByCodigo(codigo);
        if (venta.isPresent()) {
            this.ventaRepository.delete(venta.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Venta> listAll(Integer pageNo, Integer pageSize) {
        String sortBy = "codigo";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Venta> pagedResult = this.ventaRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}