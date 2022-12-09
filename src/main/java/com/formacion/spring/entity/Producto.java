package com.formacion.spring.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="productos")
public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idClave;
	@Column(nullable = false)

	private String nombre;
	@Column(nullable = false)

	private String descripcion;
	@Column(nullable = false)

	private float precio;
	@Column(nullable = false)

	private int existencias;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producto", nullable=false)
	private Venta venta;*/
	
	public long getIdClave() {
		return idClave;
	}

	public void setIdClave(long idClave) {
		this.idClave = idClave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
}
