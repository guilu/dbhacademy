package com.dbhacademy.firefight.model.entity;

import com.dbhacademy.firefight.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "TB05_EXAMEN", schema = "BDD_FIREFIGHT")
public class Examen extends BaseEntity {

    @Size(max = 1024)
    @Column
    private String texto;

    @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pregunta> preguntas;

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
