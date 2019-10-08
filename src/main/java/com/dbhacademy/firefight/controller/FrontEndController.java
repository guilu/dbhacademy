package com.dbhacademy.firefight.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.service.AgrupacionService;

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
        model.addAttribute("temasSeleccionados", new TemasSeleccionados());
        return "index";
    }

    @PostMapping("/test")
    public String pregunta(@ModelAttribute TemasSeleccionados temasSeleccionados,Model model){
        LOG.info("recibo del form {} temas: {}", temasSeleccionados.getTemas().size(), temasSeleccionados.getTemasNames());
        model.addAttribute("temasSeleccionados", temasSeleccionados);
        return "test";
    }
}
