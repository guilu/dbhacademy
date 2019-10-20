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
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("menu", "test");
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

        LOG.info("Preguntas generadas:{}", preguntas.size());
        if (preguntas.size() == 0)
            preguntas = null; //me lo cargo para que no sea un array vacio sobre el que thymeleaf no puede iterar

        model.addAttribute("contadoresTest", contadoresTest);
        session.setAttribute("preguntas", preguntas);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("menu", "test");
        return "test";
    }

    @PostMapping("/pasaPregunta")
    public String pasaPregunta(@ModelAttribute ContadoresTest contadoresTest, Model model, HttpSession session) {
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");

        LOG.info("paso Pregunta {} de {}", contadoresTest.getCurrent(), preguntas.size());

        model.addAttribute("contadoresTest", contadoresTest);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("menu", "test");
        return "test";
    }


    @RequestMapping("/simulacro/{numPreguntasTotales}")
    public String simulacro(@PathVariable int numPreguntasTotales, Model model, HttpSession session) {

        //Es lo mismo que un test, pero los temasSeleccionados son random y las preguntas son totales:
        //50 75 o 100
        //Se cogen preguntas de cada tema en round robin. Para que las preguntas del mismo tema salgan juntas

        List<Pregunta> preguntas = testService.generaSimulacro(numPreguntasTotales);
        LOG.info("Preguntas totales para el simulacro: {}", numPreguntasTotales);
        LOG.info("Preguntas sacadas: {}", preguntas.size());

        session.setAttribute("preguntas", preguntas);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("menu", "simulacro");
        return "simulacro";
    }


    @PostMapping("/bsucar")
    public String buscar(@RequestParam String textoABuscar) {
        LOG.info("voy a ver done encuentro {}", textoABuscar);
        return "resultado-buscar";
    }
}
