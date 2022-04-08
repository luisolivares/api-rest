package com.example.SpringData.repository;

import com.example.SpringData.model.entities.MaquinaRegistradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("maquinaRegistradoraRepository")
public interface MaquinaRegistradoraRepository extends JpaRepository<MaquinaRegistradora, Serializable> {

    @Query(value = "SELECT m FROM MaquinaRegistradora m WHERE m.codigo = :codigo ")
    Optional<MaquinaRegistradora> findByCodigo(@Param("codigo") Integer codigo);

}
