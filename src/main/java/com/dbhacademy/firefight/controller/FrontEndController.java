package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.dto.Test;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.service.AgrupacionService;
import com.dbhacademy.firefight.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FrontEndController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontEndController.class);

    private AgrupacionService agrupacionService;
    private TestService testService;

    @Autowired
    public FrontEndController(AgrupacionService agrupacionService, TestService testService) {
        this.agrupacionService = agrupacionService;
        this.testService = testService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("agrupaciones", this.agrupacionService.getAgrupaciones());
        model.addAttribute("temasSeleccionados", new TemasSeleccionados());
        return "index";
    }

    @PostMapping("/test")
    public String generarTest(@ModelAttribute TemasSeleccionados temasSeleccionados, Model model, HttpSession session) {
        temasSeleccionados.setTemasNotNull();
        LOG.info("recibo del form {} tema(s): {}", temasSeleccionados.getTemas().size(), temasSeleccionados.toString());
        LOG.info("{} preguntas por tema.", temasSeleccionados.getNumPreguntasPorTema());

        //generamos el test con los temasSeleccionados y el numero de preguntas para dejar en la sesion una lista de Preguntas-Respuestas.
        Test test = testService.generaTest(temasSeleccionados);

        LOG.info("test generado");

        session.setAttribute("test", test);
        model.addAttribute("test",test);
        return "test";
    }
}
