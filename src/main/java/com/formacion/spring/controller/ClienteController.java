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

import com.formacion.spring.entity.Cliente;
import com.formacion.spring.service.ClienteService;


@RestController
@RequestMapping("api")
public class ClienteController {
	@Autowired
	private ClienteService servicio; 
	
	@GetMapping("clientes")
	public List<Cliente> index(){
		return servicio.mostrarClientes();
	}
	
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> show(@PathVariable long id) {
		Cliente cliente = null;
		Map<String,Object> response =new HashMap<>();
		
		try {
			cliente = servicio.buscarCliente(id);
			
			if(cliente == null) {
				response.put("mensaje", "El cliente con ID: "+id+" no existe en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			//si hay error desde la base de datos
			response.put("mensaje","Error al realizar consulta en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
				
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	@PostMapping("clientes")
	public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String,Object> response =new HashMap<>();
		
		try {
			clienteNew = servicio.guardarCliente(cliente);
		} catch (DataAccessException e) {
			//si hay error desde la base de datos
			response.put("mensaje","Error al realizar insert en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido creado con éxito!");
		response.put("cliente",clienteNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	@PutMapping("clientes/{id}")
	public ResponseEntity<?> update(@PathVariable long id,@RequestBody Cliente cliente) {
		Cliente clienteUpdate = null;
		Map<String,Object> response =new HashMap<>();
		
		try {
			
			clienteUpdate = servicio.buscarCliente(id);
			if(clienteUpdate != null) {
				clienteUpdate.setNombre(cliente.getNombre());
				clienteUpdate.setApellido(cliente.getApellido());
				clienteUpdate.setTelefono(cliente.getTelefono());
				clienteUpdate.setSexo(cliente.getSexo());
			}else {
				response.put("mensaje","Error: no se puede editar, el cliente con ID: "+id+" no existe en la base de datos");	
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			}
			
			servicio.guardarCliente(clienteUpdate);
			
		} catch (DataAccessException e) {
			//si hay error desde la base de datos
			response.put("mensaje","Error al realizar update en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje","El cliente ha sido actualizado con éxito!");
		response.put("cliente",clienteUpdate);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	@DeleteMapping("clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		Map<String,Object> response =new HashMap<>();
		Cliente clienteDelete = null;
		
		try {
			
			clienteDelete = servicio.borrarCliente(id);
		} catch (DataAccessException e) {
			//si hay error desde la base de datos
			response.put("mensaje","Error al realizar delete en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido eliminado con éxito!");
		response.put("cliente", clienteDelete);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
}
