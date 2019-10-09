package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.repository.RespuestaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RespuestaService {

    private RespuestaJpaRepository respuestaJpaRepository;

    @Autowired
    public RespuestaService(RespuestaJpaRepository respuestaJpaRepository) {
        this.respuestaJpaRepository = respuestaJpaRepository;
    }

    public List<Respuesta> scramble(List<Respuesta> respuestas){
        Collections.shuffle(respuestas);
        return respuestas;
    }
}
