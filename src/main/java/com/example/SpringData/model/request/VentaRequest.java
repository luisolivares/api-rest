package com.example.SpringData.model.request;

import com.example.SpringData.model.entities.Cajero;
import com.example.SpringData.model.entities.MaquinaRegistradora;
import com.example.SpringData.model.entities.Productos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaRequest implements Serializable {

    @JsonProperty("cajero_id")
    private Integer idCajero;

    @JsonProperty("maquina_id")
    private Integer idMaquinaRegistradora;

    @JsonProperty("productos_id")
    private Integer idProductos;

}
