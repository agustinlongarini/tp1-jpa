package com.utn.trabajo1;

import com.utn.trabajo1.entidades.*;
import com.utn.trabajo1.enumeraciones.Estados;
import com.utn.trabajo1.enumeraciones.FormaPago;
import com.utn.trabajo1.enumeraciones.TipoEnvio;
import com.utn.trabajo1.enumeraciones.TipoProducto;
import com.utn.trabajo1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class Trabajo1Application {

	@Autowired
	ClienteRepository clienterepo;

	@Autowired
	DomicilioRepository domiciliorepo;

	@Autowired
	PedidoRepository pedidorepo;

	@Autowired
	FacturaRepository facturarepo;

	@Autowired
	DetallePedidoRepository detallerepo;

	@Autowired
	ProductoRepository productorepo;

	@Autowired
	RubroRepository rubrorepo;

	public static void main(String[] args) {
		SpringApplication.run(Trabajo1Application.class, args);
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo, DomicilioRepository domicilioRepo, PedidoRepository pedidoRepo, FacturaRepository facturaRepo, DetallePedidoRepository detalleRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			//Creacion de rubros
			Rubro rubro = Rubro.builder()
					.denominacion("Postres")
					.build();

			//Crear productos
			Producto producto1 = Producto.builder()
					.tipo(TipoProducto.Manufacturado)
					.tiempoEstimadoCocina(15)
					.denominacion("Chocotorta")
					.precioVenta(109.99)
					.precioCompra(65)
					.stockActual(10)
					.stockMinimo(2)
					.unidadMedida("gramos")
					.receta("Galletas, dulce de leche, queso crema, cafe y cacao")
					.build();

			Producto producto2 = Producto.builder()
					.tipo(TipoProducto.Manufacturado)
					.tiempoEstimadoCocina(20)
					.denominacion("Flan")
					.precioVenta(99.99)
					.precioCompra(57)
					.stockActual(8)
					.stockMinimo(1)
					.unidadMedida("gramos")
					.receta("Azucar, huevos, leche y esencia de vainilla")
					.build();

			Producto producto3 = Producto.builder()
					.tipo(TipoProducto.Insumo)
					.tiempoEstimadoCocina(0)
					.denominacion("Helado")
					.precioVenta(75)
					.precioCompra(40)
					.stockActual(17)
					.stockMinimo(5)
					.unidadMedida("gramos")
					.receta("Vainilla, chocolate y frutilla")
					.build();

			//Asignar productos al rubro
			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);
			rubro.agregarProducto(producto3);

			//Guardar rubro
			rubrorepo.save(rubro);

			//Crear un cliente
			Cliente cliente = Cliente.builder()
					.nombre("Agustin")
					.apellido("Longarini")
					.email("agustinlong7@gmail.com")
					.telefono("2634948742")
					.build();

			//Crear domicilios
			Domicilio domicilio = Domicilio.builder()
					.calle("Menendez")
					.numero("235")
					.localidad("San Martin")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Primera Junta")
					.numero("120")
					.localidad("Palmira")
					.build();

			//Asignar domicilios a un cliente
			cliente.agregarDomicilio(domicilio);
			cliente.agregarDomicilio(domicilio2);

			//Crear detalles de pedido
			DetallePedido detallep1 = DetallePedido.builder()
					.cantidad(5)
					.subtotal(549.99)
					.build();

			DetallePedido detallep1_2 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(440)
					.build();

			DetallePedido detallep2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(100)
					.build();

			DetallePedido detallep2_2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(150)
					.build();

			//Crear pedidos
			Pedido pedido1 = Pedido.builder()
					.estado(Estados.Iniciado)
					.fecha(new Date(123, 10, 12))
					.tipoEnvio(TipoEnvio.Retira)
					.total(500)
					.build();

			Pedido pedido2 = Pedido.builder()
					.estado(Estados.Preparacion)
					.fecha(new Date(123, 10, 13))
					.tipoEnvio(TipoEnvio.Delivery)
					.total(275)
					.build();

			//Crear facturas
			Factura factura1 = Factura.builder()
					.numero(1)
					.fecha(new Date(123, 8, 12))
					.descuento(49.99)
					.formaPago(FormaPago.Efectivo)
					.total(500)
					.build();

			Factura factura2 = Factura.builder()
					.numero(2)
					.fecha(new Date(123, 8, 13))
					.descuento(0)
					.formaPago(FormaPago.Tarjeta)
					.total(250)
					.build();

			//Asignar detalles a los pedidos
			pedido1.agregarDetalle(detallep1);
			pedido1.agregarDetalle(detallep1_2);
			pedido2.agregarDetalle(detallep2);
			pedido2.agregarDetalle(detallep2_2);

			//Asignar una factura al pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			//Asignar pedidos al cliente
			cliente.hacerPedido(pedido1);
			cliente.hacerPedido(pedido2);

			//Asignar un producto al detalle
			detallep1.setProducto(producto1);
			detallep1_2.setProducto(producto2);
			detallep2.setProducto(producto2);
			detallep2_2.setProducto(producto3);

			//Guardar cliente
			clienterepo.save(cliente);

			Cliente clienteRecuperado = clienterepo.findById(cliente.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();
			}

			Rubro rubroRecuperado = rubrorepo.findById(rubro.getId()).orElse(null);
			if(rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}

		};

	}

}
