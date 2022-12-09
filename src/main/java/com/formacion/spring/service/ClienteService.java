package com.formacion.spring.service;

import java.util.List;

import com.formacion.spring.entity.Cliente;


public interface ClienteService {
	public List<Cliente> mostrarClientes();
	
	public Cliente buscarCliente(long id);
	
	public Cliente guardarCliente(Cliente cliente);
	
	public Cliente borrarCliente(long id);
	
	}
