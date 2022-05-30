package com.dbhacademy.firefight.model.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.dbhacademy.firefight.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB03_PREGUNTA", schema = "BDD_FIREFIGHT")
public class Pregunta extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "TEMA_ID", referencedColumnName = "ID")
	@JsonIgnore
	private Tema tema;

	@Size(max = 1024)
	private String texto;

	@OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Respuesta> respuestas;

	@ManyToOne
	@JoinColumn(name = "EXAMEN_ID", referencedColumnName = "ID")
	@JsonIgnore
	private Examen examen;


	public Pregunta(){

	}

	public Pregunta(String texto) {
		this.texto = texto;
	}


	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}
}
