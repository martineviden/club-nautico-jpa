package com.jpa.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "barcos")
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_barco")
	private Long id;
	
	@Column(name = "nombre_barco")
	private String nombre;
	
	@Column(name = "matricula_barco")
	private int matricula;
	
	@Column(name = "numero_amarre_barco")
	private int numeroAmarre;
	
	@Column(name = "cuota_barco")
	private int cuota;
	
	@ManyToOne
	@JoinColumn(name = "propietario")
	@JsonBackReference("socio-barcos")
	private Socio propietario;
	
	@OneToMany(mappedBy = "barco")
	@JsonManagedReference("barco-salidas")
	private List<Salida> salidas;
	
	public Barco() {
		
	}
	
	public Barco(String nombre, int matricula, int numeroAmarre, int cuota, Socio propietario) {
		this.nombre = nombre;
		this.matricula = matricula;
		this.numeroAmarre = numeroAmarre;
		this.cuota = cuota;
		this.propietario = propietario;
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

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getNumeroAmarre() {
		return numeroAmarre;
	}

	public void setNumeroAmarre(int numeroAmarre) {
		this.numeroAmarre = numeroAmarre;
	}

	public int getCuota() {
		return cuota;
	}

	public void setCuota(int cuota) {
		this.cuota = cuota;
	}

	public Socio getPropietario() {
		return propietario;
	}

	public void setPropietario(Socio propietario) {
		this.propietario = propietario;
	}

	public List<Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}
	
}
