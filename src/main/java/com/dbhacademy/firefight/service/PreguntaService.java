package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.PreguntaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PreguntaService {

    private PreguntaJpaRepository preguntaJpaRepository;

    @Autowired
    public PreguntaService(PreguntaJpaRepository preguntaJpaRepository) {
        this.preguntaJpaRepository = preguntaJpaRepository;
    }
    
    public List<Pregunta> getPreguntas(){
    	return this.preguntaJpaRepository.findAll();
    }
    
    public Optional<Pregunta> getById(Long id) {
    	return this.preguntaJpaRepository.findById(id);
    }

    public List<Pregunta> getAllByTema(Optional<Tema> tema) {
        return this.preguntaJpaRepository.findByTema(tema);
    }

    /**
     *  random tambien de las respuestas de las preguntas....
     *
     * @param preguntas
     * @return
     */
    public List<Pregunta> scramble(List<Pregunta> preguntas){
        Collections.shuffle(preguntas);
        for( Pregunta pregunta : preguntas){
            Collections.shuffle(pregunta.getRespuestas());
        }
        return preguntas;
    }
}
