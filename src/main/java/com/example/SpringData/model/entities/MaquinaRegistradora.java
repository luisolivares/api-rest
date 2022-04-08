package com.example.SpringData.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MaquinaRegistradora")
@Table(name = "maquina_registradora")
public class MaquinaRegistradora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "piso")
    private Integer piso;

    @OneToMany(mappedBy = "maquinaRegistradora", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Venta> venta;

}
