package com.dbhacademy.firefight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.TemaJpaRepository;

@Service
public class TemaService {

	private TemaJpaRepository temaJpaRepository;

	@Autowired
	public TemaService(TemaJpaRepository temaJpaRepository) {
		this.temaJpaRepository = temaJpaRepository;
	}

	public List<Tema> getTemas() {
		return this.temaJpaRepository.findAll();
	}

}
