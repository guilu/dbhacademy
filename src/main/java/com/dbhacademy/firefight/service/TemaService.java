package com.dbhacademy.firefight.service;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.repository.TemaJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TemaService {

    private static final Logger LOG = LoggerFactory.getLogger(TemaService.class);

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

    public Optional<Tema> getTemaById(Long id) {
        return this.temaJpaRepository.findById(id);
    }
    
    public List<Tema> getTemasConPreguntas(){
    	return this.temaJpaRepository.findTemasConPreguntas();
    }

    /**
     * random tambien de las respuestas de las preguntas....
     *
     * @param temas
     * @return
     */
    public List<Tema> scramble(List<Tema> temas, int preguntasPorTema) {
        Collections.shuffle(temas);
        for (Tema tema : temas) {
            Collections.shuffle(this.preguntaService.scramble(tema.getPreguntas()));
        }
        return temas;
    }

    /**
     * Selecciona un tema al azahar :P
     *
     * @return
     */
    public List<Pregunta> getTemasYPreguntasRandom(int numPreguntasTotales) {

		List<Pregunta> flattenPreguntas = new ArrayList<>();

        List<Tema> temas = this.temaJpaRepository.findTemasConPreguntas();
        LOG.info("Hay {} temas con preguntas", temas.size());
        int temasSize = temas.size();

        if(temasSize>0){
			int preguntasXTemas = numPreguntasTotales / temasSize;
			int resto = numPreguntasTotales % temasSize;
			LOG.info("De cada tema hay que sacar {} preguntas si puedo", preguntasXTemas);
            LOG.info("El resto son {}", resto);

			//En realidad voy a sacar de cad tema dsponible preguntasXTemas así que:
			for (int temaCurrent = 0; temaCurrent < temasSize; temaCurrent++) {
				//los temas tienen que salir en orden así que hay que sacar el numero de preguntas de cada tema
                // y hasta los restos :P una pregunta más
                if (resto>0) {
                    flattenPreguntas.addAll(temas.get(temaCurrent).getPreguntasRandom(preguntasXTemas + 1));
                } else {
                    flattenPreguntas.addAll(temas.get(temaCurrent).getPreguntasRandom(preguntasXTemas));
                }
                resto--;
			}
		}
        return flattenPreguntas;
    }
}
