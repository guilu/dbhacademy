package com.dbhacademy.firefight.controller;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.service.AgrupacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {


    private AgrupacionService agrupacionService;

    @Autowired
    public FrontEndController(AgrupacionService agrupacionService) {
        this.agrupacionService = agrupacionService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("agrupaciones", this.agrupacionService.getAgrupaciones());
        return "index";
    }
}
