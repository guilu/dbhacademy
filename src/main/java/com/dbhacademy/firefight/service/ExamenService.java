package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.entity.Examen;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.repository.ExamenRepository;
import com.dbhacademy.firefight.repository.PreguntaJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    private ExamenRepository examenRepository;
    private PreguntaJpaRepository preguntaJpaRepository;

    public ExamenService(ExamenRepository examenRepository, PreguntaJpaRepository preguntaJpaRepository) {
        this.examenRepository = examenRepository;
        this.preguntaJpaRepository = preguntaJpaRepository;
    }

    public List<Examen> getExamenes(){
        return this.examenRepository.findAll();
    }

    public Optional<Examen> getExamen(Long id){
        return this.examenRepository.findById(id);
    }

}
