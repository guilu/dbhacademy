package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.entity.Examen;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.ExamenRepository;
import com.dbhacademy.firefight.repository.PreguntaJpaRepository;
import com.dbhacademy.firefight.repository.RespuestaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PreguntaService {

    private PreguntaJpaRepository preguntaJpaRepository;
    private RespuestaJpaRepository respuestaJpaRepository;

    @Autowired
    public PreguntaService(PreguntaJpaRepository preguntaJpaRepository, RespuestaJpaRepository respuestaJpaRepository) {
        this.preguntaJpaRepository = preguntaJpaRepository;
        this.respuestaJpaRepository = respuestaJpaRepository;
    }
    
    public List<Pregunta> getPreguntas(){
    	return this.preguntaJpaRepository.findAll();
    }
    
    public Optional<Pregunta> getById(Long id) {
    	return this.preguntaJpaRepository.findById(id);
    }

    public List<Pregunta> getAllByTema(Optional<Tema> tema) {
        return this.preguntaJpaRepository.findByTema(tema);
    }

    /**
     *  random de las preguntas y tambien de las respuestas de las preguntas....
     *
     * @param preguntas
     * @return
     */
    public List<Pregunta> scramble(List<Pregunta> preguntas){
        Collections.shuffle(preguntas);
        for( Pregunta pregunta : preguntas){
            Collections.shuffle(pregunta.getRespuestas());
        }
        return preguntas;
    }

    /**
     *  random solo de las respuestas
     * @param preguntas
     * @return
     */
    public List<Pregunta> scrambleRespuestas(List<Pregunta> preguntas) {
        for(Pregunta pregunta : preguntas) {
            Collections.shuffle(pregunta.getRespuestas());
        }
        return preguntas;
    }

    public List<Pregunta> searchInPregunta(String textoABuscar) {
        return this.preguntaJpaRepository.findByTextoContainingIgnoreCase(textoABuscar);
    }

    public Pregunta getPreguntaContainingRespuesta(Long idRespuesta) {
        Optional<Respuesta> respuesta = this.respuestaJpaRepository.findById(idRespuesta);
        return this.preguntaJpaRepository.findPreguntaByRespuestasContains(respuesta);
    }

   public List<Pregunta> getPreguntasDeExamen(Optional<Examen> examen){
        return this.preguntaJpaRepository.findByExamen(examen);
   }
}
