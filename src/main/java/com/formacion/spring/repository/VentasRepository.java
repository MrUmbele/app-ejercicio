package com.formacion.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.spring.entity.Venta;
@Repository

public interface VentasRepository extends CrudRepository<Venta, Long>{

}
