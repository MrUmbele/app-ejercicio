package com.formacion.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="APIREST EJERCICIO",version="1.0",description="Crud completo de Clientes, Ventas y Productos"))

public class AppEjercicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEjercicioApplication.class, args);
	}

}
