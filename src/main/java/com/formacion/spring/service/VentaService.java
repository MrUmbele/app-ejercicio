package com.formacion.spring.service;

import java.util.List;

import com.formacion.spring.entity.Venta;

public interface VentaService {
	public List<Venta> mostrarVentas();
	
	public Venta buscarVenta(long id);
	
	public Venta guardarVenta(Venta Venta);
	
	public Venta borrarVenta(long id);
	
}
