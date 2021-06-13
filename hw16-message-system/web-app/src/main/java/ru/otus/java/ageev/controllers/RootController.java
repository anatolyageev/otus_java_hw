package ru.otus.java.ageev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String defaultPage(){
        return "index";
    }

    @GetMapping("/serverWs")
    public String serverTimePage(){
        return "serverWsTime";
    }

}
