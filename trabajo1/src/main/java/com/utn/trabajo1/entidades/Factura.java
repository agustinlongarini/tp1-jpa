package com.utn.trabajo1.entidades;

import com.utn.trabajo1.enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad{

    private int numero;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private double descuento;
    private FormaPago formaPago;
    private int total;

}
