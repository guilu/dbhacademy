package com.dbhacademy.firefight.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

	@Size(max = 50)
	@Column(unique = true)
	private String codigo;

	@Size(max = 255)
	private String texto;

	@OneToMany(mappedBy = "pregunta")
	private List<Respuesta> respuestas;

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

}
