package com.formacion.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.spring.entity.Producto;
import com.formacion.spring.entity.Venta;
import com.formacion.spring.service.ProductoService;
import com.formacion.spring.service.VentaService;

@RestController
@RequestMapping("api")
public class VentaController {
	@Autowired
	private VentaService servicio;

	@Autowired
	private ProductoService servicioProducto;

	@GetMapping("ventas")
	public List<Venta> index() {
		return servicio.mostrarVentas();
	}

	@GetMapping("ventas/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Venta venta = null;
		Map<String, Object> response = new HashMap<>();

		try {
			venta = servicio.buscarVenta(id);

			if (venta == null) {
				response.put("mensaje", "La venta con ID: " + id + " no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			// si hay error desde la base de datos
			response.put("mensaje", "Error al realizar consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Venta>(venta, HttpStatus.OK);
	}

	@PostMapping("ventas")
	public ResponseEntity<?> save(@RequestBody Venta venta) {
		Venta ventaNew = null;
		Producto producto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			if (venta.getCantidad() > 0) {
				if (venta.getIva() >= 0) {
					ventaNew = servicio.guardarVenta(venta);
					producto = servicioProducto.buscarProducto(venta.getProducto().getIdClave());
					if (producto != null) {
						ventaNew.setSubtotal(producto.getPrecio() * ventaNew.getCantidad());
						ventaNew.setTotal((1 + ventaNew.getIva() / 100) * ventaNew.getSubtotal());
					}
				} else {
					response.put("mensaje", "Error al añadir venta, " + venta.getIva() + " es una IVA no valido");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.put("mensaje", "Error al añadir venta, " + venta.getCantidad() + " es una cantidad no valida");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

			}

		} catch (DataAccessException e) {
			// si hay error desde la base de datos
			response.put("mensaje", "Error al realizar insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La venta ha sido creada con éxito!");
		response.put("venta", ventaNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("ventas/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Venta venta) {
		Venta ventaUpdate = null;
		Producto producto = null;

		Map<String, Object> response = new HashMap<>();

		try {

			if (servicio.buscarVenta(id) != null) {
				ventaUpdate = servicio.buscarVenta(id);
				producto = servicioProducto.buscarProducto(ventaUpdate.getProducto().getIdClave());

				if (ventaUpdate.getCantidad() >= 0) {
					if (ventaUpdate.getIva() >= 0) {

						ventaUpdate.setCantidad(venta.getCantidad());
						ventaUpdate.setCliente(venta.getCliente());
						ventaUpdate.setIva(venta.getIva());
						ventaUpdate.setProducto(venta.getProducto());
						ventaUpdate.setSubtotal(producto.getPrecio() * ventaUpdate.getCantidad());
						ventaUpdate.setTotal((1 + ventaUpdate.getIva() / 100) * ventaUpdate.getSubtotal());
					} else {
						response.put("mensaje",
								"Error al editar la venta, " + venta.getIva() + " es una IVA no valido");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
					}
				} else {
					response.put("mensaje",
							"Error al editar la venta, " + venta.getCantidad() + " es una cantidad no valida");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				response.put("mensaje",
						"Error: no se puede editar, la venta con ID: " + id + " no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			servicio.guardarVenta(ventaUpdate);

		} catch (DataAccessException e) {
			// si hay error desde la base de datos
			response.put("mensaje", "Error al realizar update en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La venta ha sido actualizada con éxito!");
		response.put("venta", ventaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("ventas/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		Venta ventaDelete = null;

		try {

			ventaDelete = servicio.borrarVenta(id);
		} catch (DataAccessException e) {
			// si hay error desde la base de datos
			response.put("mensaje", "Error al realizar delete en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La venta ha sido eliminada con éxito!");
		response.put("venta", ventaDelete);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
