package com.jpa.main.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salidas")
public class Salida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salida")
	private Long id;
	
	@Column(name = "fecha_salida")
	private Date fechaSalida;
	
	@Column(name = "destino_salida")
	private String destino;
	
	@ManyToOne
	@JoinColumn(name = "barco_id")
	@JsonBackReference("barco-salidas")
	private Barco barco;
	
	@ManyToOne
	@JoinColumn(name = "patron_id")
	@JsonBackReference("patron-salidas")
	private Patron patron;
	
	public Salida() {
		
	}
	
	public Salida(Date fechaSalida, String destino, Barco barco, Patron patron) {
		this.fechaSalida = fechaSalida;
		this.destino = destino;
		this.barco = barco;
		this.patron = patron;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
}
