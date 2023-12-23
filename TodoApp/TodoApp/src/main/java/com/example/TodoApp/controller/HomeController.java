package com.example.TodoApp.controller;

import com.example.TodoApp.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String Home(Model model){
        model.addAttribute ("today", LocalDate.now());
        model.addAttribute("user", new UserDTO("stefani", "steff@abv.bg"));
        return "homePage";
    }

    @GetMapping("/login")
    public String Login(Model model){
        model.addAttribute("user", new UserDTO("stefani", "steff@abv.bg"));
        return "login";
    }

}
