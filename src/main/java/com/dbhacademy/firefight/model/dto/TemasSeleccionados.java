package com.dbhacademy.firefight.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.dbhacademy.firefight.model.entity.Tema;


public class TemasSeleccionados {

    private List<Tema> temas;
    private int numPreguntasPorTema;

    public TemasSeleccionados() {
    }

    public TemasSeleccionados(List<Tema> temas, int numPreguntasPorTema) {
        this.temas = temas;
        this.numPreguntasPorTema = numPreguntasPorTema;
    }

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

    /**
     * Deja la lista de temas solo con los no vacios
     * pues el bindindg del formulario con la lista de temas deja los que no se hayan seleccionado como null.
     *
     */
    public void setTemasNotNull() {
        List<Tema> noVacios = new ArrayList<>();
        for (Tema tema : this.temas) {
            if (tema != null) {
                noVacios.add(tema);
            }
        }
        this.temas = null;
        this.temas = noVacios;
    }

    /**
     * Sobrecarga del toString
     *
     */
    public String toString() {
        StringBuffer txtTemas = new StringBuffer();
        txtTemas.append("[");
        if(temas!= null) {
            for (int t = 0; t < temas.size(); t++) {
                txtTemas.append("'" + temas.get(t).getTexto() + "'");
                if (t < temas.size() - 1) {
                    txtTemas.append(",");
                }
            }
        }
        return txtTemas.append("]").toString();
    }


}
