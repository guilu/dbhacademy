package com.dbhacademy.firefight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.repository.AgrupacionJpaRepository;

@Service
public class AgrupacionService {
	
	private AgrupacionJpaRepository agrupacionJpaRepository;

	@Autowired
	public AgrupacionService(AgrupacionJpaRepository agrupacionJpaRepository) {
		this.agrupacionJpaRepository = agrupacionJpaRepository;
	}
	
	public List<Agrupacion> getAgrupaciones(){
		return this.agrupacionJpaRepository.findAll();
	}
}
