package com.formacion.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.formacion.spring.entity.Venta;
import com.formacion.spring.repository.VentasRepository;

@Service
public class VentasServiceImpl implements VentaService {

	@Autowired
	private VentasRepository repositorio;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> mostrarVentas() {
		return (List<Venta>) repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Venta buscarVenta(long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Venta guardarVenta(Venta Venta) {
		return repositorio.save(Venta);
	}

	@Override
	@Transactional
	public Venta borrarVenta(long id) {
		Venta VentaBorrado = buscarVenta(id);
		repositorio.deleteById(id);
		
		return VentaBorrado;
		
		
	}

}
