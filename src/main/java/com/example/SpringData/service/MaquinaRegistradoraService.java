package com.example.SpringData.service;

import com.example.SpringData.model.entities.MaquinaRegistradora;
import com.example.SpringData.model.request.MaquinaRegistradoraRequest;

import java.util.List;

public interface MaquinaRegistradoraService {

    public MaquinaRegistradora create(MaquinaRegistradoraRequest maquinaRegistradoraRequest);

    public MaquinaRegistradora update(Integer id, MaquinaRegistradoraRequest maquinaRegistradoraRequest);

    public MaquinaRegistradora findById(Integer codigo);

    public boolean delete(Integer codigo);

    public List<MaquinaRegistradora> listAll(Integer pageNo, Integer pageSize);

}
