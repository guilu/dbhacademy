package com.dbhacademy.firefight.model.dto;

import com.dbhacademy.firefight.model.entity.Pregunta;

import java.util.List;

public class Test {

    List<Pregunta> preguntas;
    int current;
    int aciertos;
    int errores;

    public Test() {
    }

    public Test(List<Pregunta> preguntas, int aciertos, int errores) {
        this.preguntas = preguntas;
        this.aciertos = aciertos;
        this.errores = errores;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Pregunta getPreguntaCurrent() {
        if (this.preguntas != null && this.preguntas.size()>0) {
            return this.preguntas.get(this.current);
        } else
            return null;
    }
}
