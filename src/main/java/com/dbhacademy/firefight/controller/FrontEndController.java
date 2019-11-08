package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.dto.ContadoresTest;
import com.dbhacademy.firefight.model.dto.ResultadoBuscar;
import com.dbhacademy.firefight.model.dto.TemasSeleccionados;
import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.model.entity.Tema;
import com.dbhacademy.firefight.service.AgrupacionService;
import com.dbhacademy.firefight.service.SearchService;
import com.dbhacademy.firefight.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontEndController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontEndController.class);

    private AgrupacionService agrupacionService;
    private TestService testService;
    private SearchService searchService;

    @Autowired
    public FrontEndController(AgrupacionService agrupacionService, TestService testService, SearchService searchService) {
        this.agrupacionService = agrupacionService;
        this.testService = testService;
        this.searchService = searchService;
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
        session.setAttribute("contadoresTest", contadoresTest);
        model.addAttribute("preguntas", preguntas);
        session.setAttribute("preguntasFalladas", new ArrayList<>());
        session.setAttribute("preguntas", preguntas);
        model.addAttribute("menu", "test");
        return "test";
    }

    @PostMapping("/pasaPregunta")
    public String pasaPregunta(@ModelAttribute ContadoresTest contadoresTest, Model model, HttpSession session) {

        List<Pregunta> preguntasFalladas = (List<Pregunta>) session.getAttribute("preguntasFalladas");
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntas");

        if (contadoresTest.isFallada()) {
            preguntasFalladas.add(preguntas.get(contadoresTest.getCurrent()-1));
        }

        if(contadoresTest.getCurrent() == preguntas.size()) {
            //es la última y se va a repetir con los fallos.
            contadoresTest.setNumPreguntasTotal(preguntasFalladas.size());
            LOG.info("Preguntas falladas:{}", preguntasFalladas.size());

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
