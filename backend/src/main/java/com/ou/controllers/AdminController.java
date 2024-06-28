/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private RoomServices roomServices;
    @Autowired
    private ReportService reportService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private StatService statService;
    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("countRoom", this.roomServices.getRoomCount());
        model.addAttribute("countReport", this.reportService.countReport());
        model.addAttribute("countEvaluation", this.evaluationService.countAllEvaluations());
        model.addAttribute("statReport", this.statService.statReport());
        model.addAttribute("statRevenue", this.statService.statsRevenueByMonth());
        return "IndexStat";
    }

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("floors", this.floorServices.getFloor());

        //-----------------Firebase-----------------
        model.addAttribute("apiKey",env.getProperty("firebase.apiKey"));
        model.addAttribute("authDomain",env.getProperty("firebase.authDomain"));
        model.addAttribute("projectId",env.getProperty("firebase.projectId"));
        model.addAttribute("storageBucket",env.getProperty("firebase.storageBucket"));
        model.addAttribute("messagingSenderId",env.getProperty("firebase.messagingSenderId"));
        model.addAttribute("appId",env.getProperty("firebase.appId"));
    }
}
