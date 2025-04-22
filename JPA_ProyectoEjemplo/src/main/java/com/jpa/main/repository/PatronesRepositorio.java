package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.main.model.Patron;

@Repository
public interface PatronesRepositorio extends JpaRepository<Patron, Long> {
	
}
