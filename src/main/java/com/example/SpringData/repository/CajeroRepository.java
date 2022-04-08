package com.example.SpringData.repository;

import com.example.SpringData.model.entities.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("cajeroRepository")
public interface CajeroRepository extends JpaRepository<Cajero, Serializable> {

    @Query("SELECT c FROM Cajero c WHERE c.codigo = :codigo")
    Optional<Cajero> findByCodigo(@Param("codigo") Integer codigo);

}
