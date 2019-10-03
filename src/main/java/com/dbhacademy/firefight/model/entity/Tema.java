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
@Table(name = "TB02_TEMA", schema = "BDD_FIREFIGHT")
public class Tema extends BaseEntity {

	private static final long serialVersionUID = 2L;

	@ManyToOne
	@JoinColumn(name = "AGRUPACION_ID", referencedColumnName = "ID")
	@JsonIgnore
	private Agrupacion agrupacion;

	@Size(max = 50)
	@Column(unique = true)
	private String codigo;
	@Size(max = 255)
	private String texto;
	
	@OneToMany(mappedBy = "tema")
	private List<Pregunta> preguntas;

	public Agrupacion getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
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

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	
}
