package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.dto.ContadoresTest;
import com.dbhacademy.firefight.model.dto.ResultadoBuscar;
import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.entity.*;
import com.dbhacademy.firefight.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FrontEndController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontEndController.class);

    private AgrupacionService agrupacionService;
    private TestService testService;
    private SearchService searchService;
    private ExamenService examenService;
    private PreguntaService preguntaService;

    @Autowired
    public FrontEndController(AgrupacionService agrupacionService, TestService testService, SearchService searchService, ExamenService examenService, PreguntaService preguntaService) {
        this.agrupacionService = agrupacionService;
        this.testService = testService;
        this.searchService = searchService;
        this.examenService = examenService;
        this.preguntaService = preguntaService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("agrupaciones", this.agrupacionService.getAgrupaciones());
        model.addAttribute("temasSeleccionados", new TemasSeleccionados());
        model.addAttribute("examenes", this.examenService.getExamenes());
        model.addAttribute("menu", "index");
        return "index";
    }

    @RequestMapping("/temas-seleccion")
    public String temasSeleccion(Model model) {
        model.addAttribute("agrupaciones", this.agrupacionService.getAgrupaciones());
        model.addAttribute("temasSeleccionados", new TemasSeleccionados());
        model.addAttribute("examenes", this.examenService.getExamenes());
        model.addAttribute("menu", "index");
        return "temas-seleccion";
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
        session.setAttribute("contadoresTest", contadoresTest);
        model.addAttribute("preguntas", preguntas);
        session.setAttribute("preguntasFalladas", new ArrayList<>());
        session.setAttribute("preguntas", preguntas);
        model.addAttribute("menu", "temas-seleccion");
        return "test";
    }

    @PostMapping("/pasaPregunta")
    public String pasaPregunta(@ModelAttribute ContadoresTest contadoresTest, Model model, HttpSession session) {

        @SuppressWarnings("unchecked")
		List<Pregunta> preguntasFalladas = (List<Pregunta>) session.getAttribute("preguntasFalladas");
        @SuppressWarnings("unchecked")
		List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");

        if (contadoresTest.isFallada()) {
            preguntasFalladas.add(preguntas.get(contadoresTest.getCurrent()-1));
        }

        if(contadoresTest.getCurrent() == preguntas.size()) {

            //es la última y se va a repetir con los fallos.
            LOG.info("Preguntas falladas:{}", preguntasFalladas.size());

            //desordenar las preguntas falladas
            preguntasFalladas = this.preguntaService.scramble(preguntasFalladas);

            if (preguntasFalladas.size() == 0)
                preguntas = null; //me lo cargo para que no sea un array vacio sobre el que thymeleaf no puede iterar

            contadoresTest.reset();
            contadoresTest.setNumPreguntasTotal(preguntasFalladas.size());
            model.addAttribute("contadoresTest", contadoresTest);
            model.addAttribute("preguntas", preguntasFalladas);

            session.setAttribute("preguntasFalladas", new ArrayList<>());
            session.setAttribute("preguntas", preguntasFalladas);

        } else {
            LOG.info("La Pregunta {} de {}: {} ", contadoresTest.getCurrent(), preguntas.size(), preguntas.get(contadoresTest.getCurrent()-1).getTexto());
            LOG.info("fallada: {}", contadoresTest.isFallada());


            session.setAttribute("preguntasFalladas", preguntasFalladas);
            model.addAttribute("contadoresTest", contadoresTest);
            model.addAttribute("preguntas", preguntas);

        }


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
        model.addAttribute("examenes", this.examenService.getExamenes());
        return "simulacro";
    }

    @RequestMapping("/examen/{id}")
    public String examen(@PathVariable int id, Model model, HttpSession session) {

        //Es lo mismo que un simulacro pero las preguntas salen del examen
        //Se cogen preguntas del examen y las respuestas se barajan.
        Optional<Examen> examen = this.examenService.getExamen((long) id);

        List<Pregunta> preguntas =  this.preguntaService.getPreguntasDeExamen(examen);

        //En los examenes las preguntas no se desordenan pero si las respuestas
        preguntas = this.preguntaService.scrambleRespuestas(preguntas);
        LOG.info("Preguntas totales para el examen: {}", examen.isPresent() ? examen.get().getTexto() : "vacio");
        LOG.info("Preguntas sacadas: {}", preguntas.size());

        session.setAttribute("preguntas", preguntas);
        model.addAttribute("preguntas", preguntas);
        model.addAttribute("menu", "examenes");
        model.addAttribute("examenes", this.examenService.getExamenes());
        return "simulacro";
    }


    @PostMapping("/buscar")
    public String buscar(@RequestParam String textoABuscar, Model model) {
        LOG.info("voy a ver si encuentro '{}' en algun campo de la bbdd", textoABuscar);

        ResultadoBuscar resultadoBuscar = new ResultadoBuscar();
        List<Agrupacion> agrupaciones = this.searchService.searchInAgrupacion(textoABuscar);
        List<Tema> temas = this.searchService.searchInTema(textoABuscar);
        List<Pregunta> preguntas = this.searchService.searchInPregunta(textoABuscar);
        List<Respuesta> respuestas = this.searchService.searchInRespuesta(textoABuscar);

        if (respuestas.size() > 0) {
            resultadoBuscar.setNumRespuestas(respuestas.size());
            resultadoBuscar.setRespuestas(respuestas);
            model.addAttribute("active","respuestas");
            model.addAttribute("respuestas",respuestas);
        }
        if (preguntas.size() > 0 ) {
            resultadoBuscar.setNumPreguntas(preguntas.size());
            resultadoBuscar.setPreguntas(preguntas);
            model.addAttribute("active","preguntas");
            model.addAttribute("preguntas",preguntas);
        }
        if (temas.size() > 0 ){
            resultadoBuscar.setNumTemas(temas.size());
            resultadoBuscar.setTemas(temas);
            model.addAttribute("active","temas");
            model.addAttribute("temas",temas);
        }
        if ( agrupaciones.size() > 0 ) {
            resultadoBuscar.setNumAgrupaciones(agrupaciones.size());
            resultadoBuscar.setAgrupaciones(agrupaciones);
            model.addAttribute("active","agrupaciones");
            model.addAttribute("agrupaciones",agrupaciones);
        }

        model.addAttribute("resultadoBuscar", resultadoBuscar);
        return "resultado-buscar";

    }
}
