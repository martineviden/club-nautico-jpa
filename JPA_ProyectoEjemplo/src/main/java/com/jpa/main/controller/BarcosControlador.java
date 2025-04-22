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
import com.jpa.main.model.Socio;
import com.jpa.main.repository.BarcosRepositorio;
import com.jpa.main.repository.SociosRepositorio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BarcosControlador {
	
	@Autowired
	private BarcosRepositorio barcosRepositorio;
	
	@Autowired
	private SociosRepositorio sociosRepositorio;
	
	@GetMapping("/barcos")
	public List<Barco> getBarcos() {
		return barcosRepositorio.findAll();
	}
	
	@GetMapping("/barco/{id}")
	public ResponseEntity<Barco> getBarcoPorId(@PathVariable(value = "id") Long barcoId) throws ResourceNotFoundException {
		Barco barco = barcosRepositorio.findById(barcoId).orElseThrow(() -> new ResourceNotFoundException("Barco no encontrado con este id: " + barcoId));
		
		return ResponseEntity.ok().body(barco);
	}
	
	@PostMapping("/barcos")
	public Barco crearBarco(@Valid @RequestBody Barco barco) {
		Socio socio = sociosRepositorio.findById(barco.getPropietario().getId()).get();
		barco.setPropietario(socio);
		
		return barcosRepositorio.save(barco);
	}
	
	@PutMapping("/barcos/{id}")
	public ResponseEntity<Barco> actualizarBarco(@PathVariable(value = "id") Long barcoId, @Valid @RequestBody Barco barcoDetalles) throws ResourceNotFoundException {
		Barco barco = barcosRepositorio.findById(barcoId).orElseThrow(() -> new ResourceNotFoundException("Barco no encontrado con este id: " + barcoId));
		
		barco.setNombre(barcoDetalles.getNombre());
		barco.setMatricula(barcoDetalles.getMatricula());
		barco.setNumeroAmarre(barcoDetalles.getNumeroAmarre());
		barco.setCuota(barcoDetalles.getCuota());
		barco.setPropietario(barcoDetalles.getPropietario());
		
		final Barco barcoActualizado = barcosRepositorio.save(barco);
		return ResponseEntity.ok(barcoActualizado);
	}
	
	@DeleteMapping("/barcos/{id}")
	public Map<String, Boolean> eliminarBarco(@PathVariable(value = "id") Long barcoId) throws ResourceNotFoundException {
		Barco barco = barcosRepositorio.findById(barcoId).orElseThrow(() -> new ResourceNotFoundException("Barco no encontrado con este id: " + barcoId));
		barcosRepositorio.delete(barco);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return respuesta;
	}
	
}