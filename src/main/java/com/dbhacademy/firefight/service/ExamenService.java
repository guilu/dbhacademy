package com.dbhacademy.firefight.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Examen;
import com.dbhacademy.firefight.repository.ExamenRepository;

@Service
public class ExamenService {

    private ExamenRepository examenRepository;

    @Autowired
    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public List<Examen> getExamenes(){
        return this.examenRepository.findAll();
    }

    public Optional<Examen> getExamen(Long id){
        return this.examenRepository.findById(id);
    }

}
