package com.formacion.spring.service;

import java.util.List;

import com.formacion.spring.entity.Producto;

public interface ProductoService {
	public List<Producto> mostrarProductos();
	public Producto buscarProducto(long id);
	public Producto guardarProducto(Producto producto);
	public Producto borrarProducto(long id);
}
