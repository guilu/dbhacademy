package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.dto.Test;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.TemaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private TemaService temaService;

    @Autowired
    public TestService(TemaService temaService) {
        this.temaService = temaService;
    }

    public Test generaTest(TemasSeleccionados temasSeleccionados){
        Test test = new Test();
        List<Pregunta> flattenPreguntas = new ArrayList<>();
        List<Tema> temas = this.temaService.scramble(temasSeleccionados.getTemas());
        for(Tema tema : temas){
            flattenPreguntas.addAll(tema.getPreguntas());
        }
        test.setPreguntas(flattenPreguntas);
        test.setAciertos(0);
        test.setErrores(0);
        test.setCurrent(0);

        return test;
    }
}
