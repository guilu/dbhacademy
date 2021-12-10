package com.dbhacademy.firefight.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.repository.AgrupacionJpaRepository;

@Service
public class AgrupacionService {
	
	private final AgrupacionJpaRepository agrupacionJpaRepository;

	@Autowired
	public AgrupacionService(AgrupacionJpaRepository agrupacionJpaRepository) {
		this.agrupacionJpaRepository = agrupacionJpaRepository;
	}
	
	public List<Agrupacion> getAgrupaciones(){
		return this.agrupacionJpaRepository.findAll();
	}

	public List<Agrupacion> scramble(List<Agrupacion> agrupaciones) {
		Collections.shuffle(agrupaciones);
		return agrupaciones;
	}

	public List<Agrupacion> searchInAgrupacion(String cadena) {
		return this.agrupacionJpaRepository.findByTextoContainingIgnoreCase(cadena);
	}
}
