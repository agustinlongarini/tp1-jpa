package com.utn.trabajo1.entidades;

import com.utn.trabajo1.enumeraciones.Estados;
import com.utn.trabajo1.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad{

    private Estados estado;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private TipoEnvio tipoEnvio;
    private double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void verFactura(){
        System.out.println("Numero: " + factura.getNumero()
                + ", Fecha: " + factura.getFecha().toString()
                + ", Descuento: " + factura.getDescuento()
                + ", Forma de pago: " + factura.getFormaPago()
                + ", Total: " + factura.getTotal());
    }

    public void agregarDetalle(DetallePedido detalle){
        detalles.add(detalle);
    }

    public void mostrarDetalles() {
        for (DetallePedido detalle : detalles){
            System.out.println("Detalles de pedido"
                    + "Cantidad: " + detalle.getCantidad()
                    + ", Subtototal: " + detalle.getSubtotal());
        }
    }

}
