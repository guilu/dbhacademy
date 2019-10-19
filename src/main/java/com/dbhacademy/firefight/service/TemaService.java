package com.dbhacademy.firefight.service;

import java.util.*;

import com.dbhacademy.firefight.model.entity.Pregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.TemaJpaRepository;

@Service
public class TemaService {

	private TemaJpaRepository temaJpaRepository;
	private PreguntaService preguntaService;

	@Autowired
	public TemaService(TemaJpaRepository temaJpaRepository, PreguntaService preguntaService) {
		this.temaJpaRepository = temaJpaRepository;
		this.preguntaService = preguntaService;
	}

	public List<Tema> getTemas() {
		return this.temaJpaRepository.findAll();
	}

	public Optional<Tema> getTemaById(Long id){
		return this.temaJpaRepository.findById(id);
	}

	/**
	 *  random tambien de las respuestas de las preguntas....
	 *
	 * @param temas
	 * @return
	 */
	public List<Tema> scramble(List<Tema> temas,int preguntasPorTema){
		Collections.shuffle(temas);
		for(Tema tema : temas){
			Collections.shuffle( this.preguntaService.scramble(tema.getPreguntas()));
		}
		return temas;
	}

	/**
	 * Selecciona un tema al azahar :P
	 * @return
	 */
	public List<Pregunta> getTemasYPreguntasRandom(int numPreguntasTotales){
		List<Tema> temas =  this.temaJpaRepository.findAll();
		List<Pregunta> flattenPreguntas = new ArrayList<>();

		for(Tema tema : temas){
			List<Pregunta> preguntas = tema.getPreguntas();

			if (preguntas.size()>0) {
				Random rand = new Random();
				Pregunta pregunta = preguntas.get(rand.nextInt(preguntas.size()));
				flattenPreguntas.add(pregunta);
			}
		}

		return flattenPreguntas;
	}
}
