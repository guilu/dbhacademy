package com.dbhacademy.firefight.service;

import java.util.Collections;
import java.util.List;

import com.dbhacademy.firefight.model.dto.NuevoTemaDTO;
import com.dbhacademy.firefight.model.entity.Tema;
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

    public List<Agrupacion> getAgrupacionesAlphabetically() {
		return this.agrupacionJpaRepository.findAllByOrderByTexto();
    }


	public Agrupacion getById(long id){
		return this.agrupacionJpaRepository.findById(id).get();

	}

    public Agrupacion saveAgrupacion(Agrupacion nuevaAgrupacion) {
		return this.agrupacionJpaRepository.save(nuevaAgrupacion);
    }

	public void deleteAgrupacion(Agrupacion agrupacion) {
		this.agrupacionJpaRepository.delete(agrupacion);
	}
}
