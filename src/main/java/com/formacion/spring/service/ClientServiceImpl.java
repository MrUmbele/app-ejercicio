package com.formacion.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacion.spring.entity.Cliente;
import com.formacion.spring.repository.ClienteRepository;

@Service
public class ClientServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> mostrarClientes() {
		return (List<Cliente>) repositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarCliente(long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente guardarCliente(Cliente cliente) {
		return repositorio.save(cliente);
	}

	@Override
	@Transactional
	public Cliente borrarCliente(long id) {
		Cliente clienteBorrado = buscarCliente(id);
		repositorio.deleteById(id);
		
		return clienteBorrado;
		
		
	}

}
