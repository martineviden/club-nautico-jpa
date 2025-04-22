package com.jpa.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.main.exception.ResourceNotFoundException;
import com.jpa.main.model.Barco;
import com.jpa.main.model.Patron;
import com.jpa.main.model.Salida;
import com.jpa.main.repository.BarcosRepositorio;
import com.jpa.main.repository.PatronesRepositorio;
import com.jpa.main.repository.SalidasRepositorio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SalidasControlador {

	@Autowired
	private SalidasRepositorio salidasRepositorio;
	
	@Autowired
	private BarcosRepositorio barcosRepositorio;
	
	@Autowired
	private PatronesRepositorio patronesRepositorio;
	
	@GetMapping("/salidas")
	public List<Salida> getSalidas() {
		return salidasRepositorio.findAll();
	}
	
	@GetMapping("/salida/{id}")
	public ResponseEntity<Salida> getSalidaPorId(@PathVariable(value = "id") long salidaId) throws ResourceNotFoundException {
		Salida salida = salidasRepositorio.findById(salidaId).orElseThrow(() -> new ResourceNotFoundException("Salida no encontrada con este id: " + salidaId));
	
		return ResponseEntity.ok().body(salida);
	}
	
	@PostMapping("/salidas")
	public Salida crearSalida(@Valid @RequestBody Salida salida) {
		Barco barco = barcosRepositorio.findById(salida.getBarco().getId()).get();
		salida.setBarco(barco);
		
		Patron patron = patronesRepositorio.findById(salida.getPatron().getId()).get();
		salida.setPatron(patron);
		
		return salidasRepositorio.save(salida);
	}
	
	@PutMapping("/salidas/{id}")
	public ResponseEntity<Salida> actualizarSalida(@PathVariable(value = "id") long salidaId, @Valid @RequestBody Salida salidaDetalles) throws ResourceNotFoundException {
		Salida salida = salidasRepositorio.findById(salidaId).orElseThrow(() -> new ResourceNotFoundException("Salida no encontrada con este id: " + salidaId));
	
		salida.setFechaSalida(salidaDetalles.getFechaSalida());
		salida.setDestino(salidaDetalles.getDestino());
		salida.setBarco(salidaDetalles.getBarco());
		salida.setPatron(salidaDetalles.getPatron());
		
		final Salida salidaActualizada = salidasRepositorio.save(salida);
		return ResponseEntity.ok(salidaActualizada);
	}
	
	@DeleteMapping("/salidas/{id}")
	public Map<String, Boolean> eliminarSalida(@PathVariable(value = "id") long salidaId) throws ResourceNotFoundException {
		Salida salida = salidasRepositorio.findById(salidaId).orElseThrow(() -> new ResourceNotFoundException("Salida no encontrada con este id: " + salidaId));
		salidasRepositorio.delete(salida);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return respuesta;
	}
	
}
