package com.dbhacademy.firefight.model.entity;

import com.dbhacademy.firefight.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB04_RESPUESTA", schema = "BDD_FIREFIGHT")
public class Respuesta extends BaseEntity {

    private static final long serialVersionUID = 4L;

    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Pregunta pregunta;

    @Size(max = 50)
    @Column(unique = true)
    private String codigo;
    @Size(max = 255)
    private String texto;
    private boolean esCorrecta;

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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

    public boolean esCorrecta() {
        return esCorrecta;
    }

    public void setCorrecta(boolean correcta) {
        esCorrecta = correcta;
    }
}
