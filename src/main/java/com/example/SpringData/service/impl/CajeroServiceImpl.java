package com.example.SpringData.service.impl;

import com.example.SpringData.model.entities.Cajero;
import com.example.SpringData.model.entities.Venta;
import com.example.SpringData.model.request.CajeroRequest;
import com.example.SpringData.repository.CajeroRepository;
import com.example.SpringData.repository.VentaRepository;
import com.example.SpringData.service.CajeroService;
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

@Service("cajeroService")
public class CajeroServiceImpl implements CajeroService {

    @Qualifier("cajeroRepository")
    private final CajeroRepository cajeroRepository;

    @Qualifier("ventaRepository")
    private final VentaRepository ventaRepository;

    @Autowired
    public CajeroServiceImpl(CajeroRepository cajeroRepository, VentaRepository ventaRepository) {
        this.cajeroRepository = cajeroRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Cajero create(CajeroRequest cajeroRequest) {
        Cajero cajero = new Cajero();
        cajero.setNombre(cajeroRequest.getNombre());
        List<Venta> venta = this.ventaRepository.listAllByCodigo(cajeroRequest.getIdVenta());
        cajero.setVenta(venta);
        Cajero result = this.cajeroRepository.save(cajero);
        return result;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Cajero update(Integer codigo, CajeroRequest cajeroRequest) {
        Optional<Cajero> cajero = this.cajeroRepository.findByCodigo(codigo);
        if (cajero.isPresent()) {
            cajero.get().setNombre(cajeroRequest.getNombre());
            cajero.get().setVenta(this.ventaRepository.listAllByCodigo(cajeroRequest.getIdVenta()));
            Cajero result = this.cajeroRepository.saveAndFlush(cajero.get());
            return result;
        } else {
            return cajero.get();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Cajero findByCodigo(Integer codigo) {
        Optional<Cajero> cajero = this.cajeroRepository.findByCodigo(codigo);
        if (!cajero.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cajero " + codigo + " not found");
        }
        return cajero.get();
    }

    @Transactional
    @Override
    public boolean delete(Integer id) {
        Optional<Cajero> cajero = this.cajeroRepository.findByCodigo(id);
        if (cajero.isPresent()) {
            this.cajeroRepository.delete(cajero.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cajero> listAll(Integer pageNo, Integer pageSize) {
        String sortBy = "codigo";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Cajero> pagedResult = this.cajeroRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Cajero>();
        }
    }
}
