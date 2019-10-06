package com.dbhacademy.firefight.model;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.model.entity.Tema;

import java.util.List;

public class Test {

    List<Tema> temas;
    int numPreguntasPorTema;

    List<Pregunta> preguntas;
    List<Respuesta> respuestas;

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public int getNumPreguntasPorTema() {
        return numPreguntasPorTema;
    }

    public void setNumPreguntasPorTema(int numPreguntasPorTema) {
        this.numPreguntasPorTema = numPreguntasPorTema;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
