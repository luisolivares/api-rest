package com.example.SpringData.repository;

import com.example.SpringData.model.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("ventaRepository")
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query(value = "SELECT v FROM Venta v WHERE v.codigo IN :codigo ")
    List<Venta> listAllByCodigo(@Param("codigo") Set<Integer> codigo);

    @Query(value = "SELECT v FROM Venta v WHERE v.codigo = :codigo ")
    Optional<Venta> findByCodigo(@Param("codigo") Integer codigo);

}
