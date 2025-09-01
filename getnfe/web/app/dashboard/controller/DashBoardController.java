package com.br.compol.getnfe.web.app.dashboard.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.compol.getnfe.core.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ModelAndView Dashboard() {
        var dashBoard = dashboardService.getDashboardData();
        var model = Map.of("dashboard", dashBoard);
        return new ModelAndView("index",model);
    }

}
