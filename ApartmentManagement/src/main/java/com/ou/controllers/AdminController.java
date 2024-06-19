/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.services.FloorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Admin
 */
@Controller
@ControllerAdvice
public class AdminController {
    @Autowired
    private FloorServices floorServices;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "IndexStat";
    }

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("floors", this.floorServices.getFloor());
    }
}
