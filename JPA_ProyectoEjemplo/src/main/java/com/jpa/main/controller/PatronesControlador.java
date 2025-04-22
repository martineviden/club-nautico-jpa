package com.jpa.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jpa.main.model.Patron;
import com.jpa.main.repository.PatronesRepositorio;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatronesControlador {

	@Autowired
	private PatronesRepositorio patronesRepositorio;
	
	@GetMapping("/patrones")
	public List<Patron> getPatrones() {
		return patronesRepositorio.findAll();
	}
	
	@GetMapping("/patron/{id}")
	public ResponseEntity<Patron> getPatronPorId(@PathVariable(value = "id") long patronId) throws ResourceNotFoundException {
		Patron patron = patronesRepositorio.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron no encontrado con este id: " + patronId));
		
		return ResponseEntity.ok().body(patron);
	}
	
	@PostMapping("/patrones")
	public Patron crearPatron(@Valid @RequestBody Patron patron) {
		return patronesRepositorio.save(patron);
	}
	
	@PutMapping("/patrones/{id}")
	public ResponseEntity<Patron> actualizarPatron(@PathVariable(value = "id") long patronId, @Valid @RequestBody Patron patronDetalles) throws ResourceNotFoundException {
		Patron patron = patronesRepositorio.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron no encontrado con este id: " + patronId));
		
		patron.setNombre(patronDetalles.getNombre());
		patron.setApellido(patronDetalles.getApellido());
		patron.setTelefono(patronDetalles.getTelefono());
		
		final Patron patronActualizado = patronesRepositorio.save(patron);
		return ResponseEntity.ok(patronActualizado);
	}
	
	@DeleteMapping("/patrones/{id}")
	public Map<String, Boolean> eliminarPatron(@PathVariable(value = "id") long patronId) throws ResourceNotFoundException {
		Patron patron = patronesRepositorio.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron no encontrado con este id: " + patronId));
		patronesRepositorio.delete(patron);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return respuesta;
	}
	
}
