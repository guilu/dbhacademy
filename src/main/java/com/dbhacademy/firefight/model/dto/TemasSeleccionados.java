package com.dbhacademy.firefight.model.dto;

import java.util.List;

import com.dbhacademy.firefight.model.entity.Tema;

public class TemasSeleccionados {

    List<Tema> temas;
    int numPreguntasPorTema;
    
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
	
	public String getTemasNames() {
		StringBuffer txtTemas = new StringBuffer();
		for(Tema tema : this.temas) {
			if(tema != null) {
				txtTemas.append("'"+tema.getTexto()+"'");
				txtTemas.append(" ");
			}
		}
		return txtTemas.toString();
	}
    
    
    
}
