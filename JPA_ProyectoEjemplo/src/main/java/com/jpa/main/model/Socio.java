package com.jpa.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "socios")
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_socio")
	private Long id;
	
	@Column(name = "nombre_socio")
	private String nombre;
	
	@Column(name = "apellido_socio")
	private String apellido;
	
	@Column(name = "direccion_socio")
	private String direccion;
	
	@Column(name = "telefono_socio")
	private String telefono;
	
	@Column(name = "correo_socio")
	private String correo;
	
	@OneToMany(mappedBy = "propietario")
	@JsonManagedReference("socio-barcos")
	private List<Barco> barcos;
	
	public Socio() {
		
	}
	
	public Socio(String nombre, String apellido, String direccion, String telefono, String correo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Barco> getBarcos() {
		return barcos;
	}

	public void setBarcos(List<Barco> barcos) {
		this.barcos = barcos;
	}
	
}