package com.dbhacademy.firefight.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.dbhacademy.firefight.model.BaseEntity;

@Entity
@Table(name = "TB01_AGRUPACION", schema = "BDD_FIREFIGHT")
public class Agrupacion extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Size(max = 50)
	@Column(unique = true)
	private String codigo;

	@Size(max = 255)
	private String texto;

	@OneToMany(mappedBy = "agrupacion")
	private List<Tema> temas;

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

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}
	
	
}
