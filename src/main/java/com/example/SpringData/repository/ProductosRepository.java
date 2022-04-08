package com.example.SpringData.repository;

import com.example.SpringData.model.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("productosRepository")
public interface ProductosRepository extends JpaRepository<Productos, Serializable> {

    @Query("SELECT p FROM Productos p WHERE p.codigo = :codigo")
    Optional<Productos> findByCodigo(@Param("codigo") Integer codigo);

}
