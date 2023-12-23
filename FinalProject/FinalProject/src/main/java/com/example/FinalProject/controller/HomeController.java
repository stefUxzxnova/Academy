package com.example.FinalProject.controller;

import com.example.FinalProject.model.EmployeesPair;
import com.example.FinalProject.utils.fileOperations.fileService.CsvFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    private final CsvFileService csvFileService;

    @Autowired
    public HomeController(CsvFileService csvFileService) {
        this.csvFileService = csvFileService;
    }

    @GetMapping("/")
    public String result(Model model){
        Optional<EmployeesPair> pair = csvFileService.getEmployeesWorkedMostTogether("test.csv");
        model.addAttribute("pair", pair);
        return "homePage";
    }

}
