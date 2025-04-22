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
import com.jpa.main.model.Socio;
import com.jpa.main.repository.SociosRepositorio;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SociosControlador {
	
	@Autowired
	private SociosRepositorio sociosRepositorio;
	
	@GetMapping("/socios")
	public List<Socio> getSocios() {
		return sociosRepositorio.findAll();
	}
	
	@GetMapping("/socio/{id}")
	public ResponseEntity<Socio> getSocioPorId(@PathVariable(value = "id") Long socioId) throws ResourceNotFoundException {
		Socio socio = sociosRepositorio.findById(socioId).orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con este id: " + socioId));
		
		return ResponseEntity.ok().body(socio);
	}
	
	@PostMapping("/socios")
	public Socio crearSocio(@Valid @RequestBody Socio socio) {
		return sociosRepositorio.save(socio);
	}
	
	@PutMapping("/socios/{id}")
	public ResponseEntity<Socio> actualizarSocio(@PathVariable(value = "id") Long socioId, @Valid @RequestBody Socio socioDetalles) throws ResourceNotFoundException {
		Socio socio = sociosRepositorio.findById(socioId).orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con este id: " + socioId));
		
		socio.setNombre(socioDetalles.getNombre());
		socio.setApellido(socioDetalles.getApellido());
		socio.setDireccion(socioDetalles.getDireccion());
		socio.setTelefono(socioDetalles.getTelefono());
		socio.setCorreo(socioDetalles.getCorreo());
		
		final Socio socioActualizado = sociosRepositorio.save(socio);
		return ResponseEntity.ok(socioActualizado);
	}
	
	@DeleteMapping("/socios/{id}")
	public Map<String, Boolean> eliminarSocio(@PathVariable(value = "id") Long socioId) throws ResourceNotFoundException {
		Socio socio = sociosRepositorio.findById(socioId).orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con este id: " + socioId));
		sociosRepositorio.delete(socio);

		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		return respuesta;
	}
	
}