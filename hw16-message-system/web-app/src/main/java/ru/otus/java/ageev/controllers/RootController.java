package ru.otus.java.ageev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String defaultPage(){
        return "serverWsTime.html";
    }
}
