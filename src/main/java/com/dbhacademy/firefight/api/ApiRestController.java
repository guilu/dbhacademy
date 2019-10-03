package com.dbhacademy.firefight.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	@Autowired
	public ApiRestController(AgrupacionService agrupacionService, TemaService temaservice) {
		this.agrupacionService = agrupacionService;
		this.temaService = temaservice;
	}

	@RequestMapping("/agrupaciones")
	public List<Agrupacion> getAgrupaciones(){
		return this.agrupacionService.getAgrupaciones();
	}
	
	@RequestMapping("/temas")
	public List<Tema> getTemas(){
		return this.temaService.getTemas();
	}
}
