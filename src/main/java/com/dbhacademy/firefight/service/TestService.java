package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.dto.ContadoresTest;
import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Tema;
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

    public List<Pregunta> generaPreguntas(TemasSeleccionados temasSeleccionados){
        List<Pregunta> flattenPreguntas = new ArrayList<>();
        List<Tema> temas = this.temaService.scramble(temasSeleccionados.getTemas(), temasSeleccionados.getNumPreguntasPorTema());
        for(Tema tema : temas){
            flattenPreguntas.addAll(tema.getPreguntas());
        }
        return flattenPreguntas;
    }

    public ContadoresTest pasaPregunta(ContadoresTest contadoresTest){
        contadoresTest.setCurrent(contadoresTest.getCurrent()+1);
        contadoresTest.setAciertos(contadoresTest.getAciertos());
        contadoresTest.setErrores(contadoresTest.getErrores());
        return contadoresTest;
    }

    public List<Pregunta> generaSimulacro(int numPreguntasTotales){
        List<Pregunta> flattenPreguntas = new ArrayList<>();

        // 50 preguntas.... recorre todos los temas cogiendo una pregunta al azar
        return temaService.getTemasYPreguntasRandom(numPreguntasTotales);
    }
}
