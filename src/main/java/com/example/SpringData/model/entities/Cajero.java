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
@Entity(name = "Cajero")
@Table(name = "cajero")
public class Cajero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "nombre")
    private String nombre;

    @JsonManagedReference
    @OneToMany(mappedBy = "cajero", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Venta> venta;

}
