package com.dbhacademy.firefight.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.repository.RespuestaJpaRepository;

@Service
public class RespuestaService {

    private RespuestaJpaRepository respuestaJpaRepository;

    @Autowired
    public RespuestaService(RespuestaJpaRepository respuestaJpaRepository) {
        this.respuestaJpaRepository = respuestaJpaRepository;
    }
    
    public List<Respuesta> getRespuestas(){
    	return this.respuestaJpaRepository.findAll();
    }

    public List<Respuesta> scramble(List<Respuesta> respuestas){
        Collections.shuffle(respuestas);
        return respuestas;
    }
    
    public List<Respuesta> getRespuestasDePregunta(Optional<Pregunta> pregunta) {
    	return this.respuestaJpaRepository.findByPregunta(pregunta);
    }

    public List<Respuesta> searchInRespuesta(String textoABuscar) {
        return this.respuestaJpaRepository.findByTextoContainingIgnoreCase(textoABuscar);
    }
}
