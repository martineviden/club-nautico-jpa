package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.main.model.Socio;

@Repository
public interface SociosRepositorio extends JpaRepository<Socio, Long> {
	
}
