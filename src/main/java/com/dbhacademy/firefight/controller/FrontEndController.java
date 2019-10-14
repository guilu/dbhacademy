package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.dto.ContadoresTest;
import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
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
import java.util.ArrayList;
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

        List<Pregunta> preguntas = testService.generaPreguntas(temasSeleccionados);
        ContadoresTest contadoresTest = new ContadoresTest();
        contadoresTest.setNumPreguntasPorTemas(temasSeleccionados.getNumPreguntasPorTema());
        contadoresTest.setNumPreguntasTotal(preguntas.size());

        model.addAttribute("contadoresTest", contadoresTest);
        session.setAttribute("preguntas", preguntas);
        model.addAttribute("preguntas", preguntas);

        return "test";
    }

    @PostMapping("/pasaPregunta")
    public String pasaPregunta(@ModelAttribute ContadoresTest contadoresTest, Model model, HttpSession session){
        List<Pregunta> preguntas = (List<Pregunta>)session.getAttribute("preguntas");

        if (contadoresTest.getCurrent() <= preguntas.size()) {
            model.addAttribute("contadoresTest", contadoresTest);
            model.addAttribute("preguntas", preguntas );
            return "test";
        } else {
            return "fin";
        }
    }

}
