package com.example.SpringData.service.impl;

import com.example.SpringData.model.entities.MaquinaRegistradora;
import com.example.SpringData.model.entities.Venta;
import com.example.SpringData.model.request.MaquinaRegistradoraRequest;
import com.example.SpringData.repository.MaquinaRegistradoraRepository;
import com.example.SpringData.repository.VentaRepository;
import com.example.SpringData.service.MaquinaRegistradoraService;
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

@Service("maquinaRegistradoraService")
public class MaquinaRegistradoraServiceImpl implements MaquinaRegistradoraService {

    @Qualifier("maquinaRegistradoraRepository")
    private final MaquinaRegistradoraRepository maquinaRegistradoraRepository;

    @Qualifier("ventaRepository")
    private final VentaRepository ventaRepository;

    @Autowired
    public MaquinaRegistradoraServiceImpl(MaquinaRegistradoraRepository maquinaRegistradoraRepository, VentaRepository ventaRepository) {
        this.maquinaRegistradoraRepository = maquinaRegistradoraRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public MaquinaRegistradora create(MaquinaRegistradoraRequest maquinaRegistradoraRequest) {
        MaquinaRegistradora maquinaRegistradora = new MaquinaRegistradora();
        maquinaRegistradora.setPiso(maquinaRegistradoraRequest.getPiso());
        List<Venta> venta = this.ventaRepository.listAllByCodigo(maquinaRegistradoraRequest.getIdVenta());
        maquinaRegistradora.setVenta(venta);
        MaquinaRegistradora result = this.maquinaRegistradoraRepository.save(maquinaRegistradora);
        return result;
    }

    @Override
    public MaquinaRegistradora update(Integer codigo, MaquinaRegistradoraRequest maquinaRegistradoraRequest) {
        Optional<MaquinaRegistradora> maquinaRegistradora = this.maquinaRegistradoraRepository.findByCodigo(codigo);
        if (maquinaRegistradora.isPresent()) {
            maquinaRegistradora.get().setPiso(maquinaRegistradoraRequest.getPiso());
            maquinaRegistradora.get().setVenta(this.ventaRepository.listAllByCodigo(maquinaRegistradoraRequest.getIdVenta()));
            MaquinaRegistradora result = this.maquinaRegistradoraRepository.saveAndFlush(maquinaRegistradora.get());
            return result;
        } else {
            return maquinaRegistradora.get();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MaquinaRegistradora findById(Integer codigo) {
        Optional<MaquinaRegistradora> maquinaRegistradora = this.maquinaRegistradoraRepository.findByCodigo(codigo);
        if(!maquinaRegistradora.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MaquinaRegistradora " + codigo + " not found");
        }
        return maquinaRegistradora.get();
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public boolean delete(Integer codigo) {
        Optional<MaquinaRegistradora> maquinaRegistradora = this.maquinaRegistradoraRepository.findByCodigo(codigo);
        if (maquinaRegistradora.isPresent()) {
            this.maquinaRegistradoraRepository.delete(maquinaRegistradora.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MaquinaRegistradora> listAll(Integer pageNo, Integer pageSize) {
        String sortBy = "codigo";
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<MaquinaRegistradora> pagedResult = this.maquinaRegistradoraRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
