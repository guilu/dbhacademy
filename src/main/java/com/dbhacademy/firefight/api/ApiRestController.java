package com.dbhacademy.firefight.api;

import java.util.List;
import java.util.Optional;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.service.AgrupacionService;
import com.dbhacademy.firefight.service.TemaService;

@RestController
@RequestMapping(value = "api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRestController {

	private AgrupacionService agrupacionService;
	private TemaService temaService;
	private PreguntaService preguntaService;
	
	
	@Autowired
	public ApiRestController(AgrupacionService agrupacionService, TemaService temaservice, PreguntaService preguntaService) {
		this.agrupacionService = agrupacionService;
		this.temaService = temaservice;
		this.preguntaService = preguntaService;
	}

	@RequestMapping("/agrupaciones")
	public List<Agrupacion> getAgrupaciones(){
		return this.agrupacionService.getAgrupaciones();
	}
	
	@RequestMapping("/temas")
	public List<Tema> getTemas(){
		return this.temaService.getTemas();
	}

	@RequestMapping("/tema/{id}/preguntas")
	public List<Pregunta> getPreguntasByTema(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "false") Boolean scramble){
		Optional<Tema> tema = this.temaService.getTemaById(id);
		if(scramble){
			return this.preguntaService.scramble(this.preguntaService.getAllByTema(tema));
		} else {
			return this.preguntaService.getAllByTema(tema);
		}

	}
}
