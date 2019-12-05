package com.dbhacademy.firefight.controller;


import com.dbhacademy.firefight.controller.FrontEndController;
import com.dbhacademy.firefight.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;

import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(FrontEndController.class)
public class FrontEndControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AgrupacionService agrupacionService;
    @MockBean
    private TestService testService;
    @MockBean
    private SearchService searchService;
    @MockBean
    private ExamenService examenService;
    @MockBean
    private PreguntaService preguntaService;

    @Test
    public void indexPageLoads() throws Exception {

        when(agrupacionService.getAgrupaciones()).thenReturn(new ArrayList<>());

        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Firefight")));
    }

    @Test
    public void indexPageHasAttributes() throws Exception {

        when(agrupacionService.getAgrupaciones()).thenReturn(new ArrayList<>());

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("agrupaciones"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("temasSeleccionados"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("menu"))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
