package com.dbhacademy.firefight.model.dto;

import com.dbhacademy.firefight.model.entity.Pregunta;

import java.util.List;

public class Simulacro {

    List<Pregunta> preguntas;

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
