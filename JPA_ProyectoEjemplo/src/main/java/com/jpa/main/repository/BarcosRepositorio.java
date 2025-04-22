package com.jpa.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.main.model.Barco;

@Repository
public interface BarcosRepositorio extends JpaRepository<Barco, Long> {
	
}
