package com.dbhacademy.firefight.service;

import java.util.ArrayList;
import java.util.List;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.dto.ContadoresTest;
import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Tema;

@Service
public class TestService {

    private final TemaService temaService;

    private final AgrupacionService agrupacionService;

    @Autowired
    public TestService(TemaService temaService, AgrupacionService agrupacionService) {
        this.temaService = temaService;
        this.agrupacionService = agrupacionService;
    }

    public List<Pregunta> generaPreguntas(TemasSeleccionados temasSeleccionados) {
        List<Pregunta> flattenPreguntas = new ArrayList<>();
        List<Tema> temas = this.temaService.scramble(temasSeleccionados.getTemas(), temasSeleccionados.getNumPreguntasPorTema());
        for (Tema tema : temas) {
            flattenPreguntas.addAll(tema.getPreguntas());
        }
        if (flattenPreguntas.size() < temasSeleccionados.getNumPreguntasPorTema()) {
            return flattenPreguntas;
        } else {
            return flattenPreguntas.subList(0, temasSeleccionados.getNumPreguntasPorTema());
        }
    }

    public ContadoresTest pasaPregunta(ContadoresTest contadoresTest) {
        contadoresTest.setCurrent(contadoresTest.getCurrent() + 1);
        contadoresTest.setAciertos(contadoresTest.getAciertos());
        contadoresTest.setErrores(contadoresTest.getErrores());
        return contadoresTest;
    }

    public List<Pregunta> generaSimulacro(int numPreguntasTotales) {
        // 50 preguntas.... recorre todos los temas cogiendo una pregunta al azar
        return temaService.getTemasYPreguntasRandom(numPreguntasTotales);
    }


    public List<Tema> getTemas() {
        return temaService.getTemas();
    }

    public List<Tema> getTemasAlphabetically() {
        return temaService.getTemasAlphabetically();
    }

    public List<Agrupacion> getAgrupacionesAlphabetically() {
        return agrupacionService.getAgrupacionesAlphabetically();
    }
}
