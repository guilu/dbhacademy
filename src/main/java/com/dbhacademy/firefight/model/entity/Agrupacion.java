package com.dbhacademy.firefight.model.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.dbhacademy.firefight.model.BaseEntity;

@Entity
@Table(name = "TB01_AGRUPACION", schema = "BDD_FIREFIGHT")
public class Agrupacion extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Size(max = 1024)
	private String texto;

	@OneToMany(mappedBy = "agrupacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tema> temas;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}
	
	
}
