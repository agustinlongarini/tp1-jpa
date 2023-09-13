package com.utn.trabajo1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto produ){
        productos.add(produ);
    }

    public void mostrarProductos(){
        System.out.println("Productos del rubro "+ denominacion);
        for (Producto producto : productos){
            System.out.println("Tipo: " + producto.getTipo()
                    + ", Tiempo estimado de cocina: " + producto.getTiempoEstimadoCocina()
                    + ", Denominacion: " + producto.getDenominacion()
                    + ", Precio de venta: " + producto.getPrecioVenta()
                    + ", Precio de compra: " + producto.getPrecioCompra()
                    + ", Stock actual: " + producto.getStockActual()
                    + ", Stock minimo: " + producto.getStockMinimo()
                    + ", Unidad de medida: " + producto.getUnidadMedida()
                    + ", Receta: " + producto.getReceta());
        }
    }

}
