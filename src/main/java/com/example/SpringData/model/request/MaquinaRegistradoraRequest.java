package com.example.SpringData.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaquinaRegistradoraRequest implements Serializable {

    @JsonProperty("piso")
    private Integer piso;

    @JsonProperty("id_venta")
    private Set<Integer> idVenta;

}
