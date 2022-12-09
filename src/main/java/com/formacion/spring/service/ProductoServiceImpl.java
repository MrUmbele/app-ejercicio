package com.formacion.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.spring.entity.Producto;
import com.formacion.spring.repository.ProductosRepository;
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductosRepository repositorio;
	@Override
	public List<Producto> mostrarProductos() {
		return (List<Producto>) repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto buscarProducto(long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto guardarProducto(Producto Producto) {
		return repositorio.save(Producto);
	}

	@Override
	@Transactional
	public Producto borrarProducto(long id) {
		Producto ProductoBorrado = buscarProducto(id);
		repositorio.deleteById(id);
		
		return ProductoBorrado;
		
		
	}

}
