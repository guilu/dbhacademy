package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.Test;
import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.service.AgrupacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontEndController.class);

    private AgrupacionService agrupacionService;

    @Autowired
    public FrontEndController(AgrupacionService agrupacionService) {
        this.agrupacionService = agrupacionService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("agrupaciones", this.agrupacionService.getAgrupaciones());
        model.addAttribute("test", new Test());
        return "index";
    }

    @GetMapping("/test")
    public String pregunta(Model model){
        LOG.info("vamos por get al test");
        model.addAttribute("test", new Test());
        return "test";
    }

    @PostMapping("/test")
    public String pregunta(@ModelAttribute Test test){
        LOG.info("recibo el form test con {} preguntas por tema", test.getNumPreguntasPorTema());
        return "test";
    }
}
