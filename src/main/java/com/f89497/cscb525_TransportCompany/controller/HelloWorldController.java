package com.f89497.cscb525_TransportCompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController{
    
    @GetMapping("/")
    public String helloWorld(){
        return "redirect:/companies";
    }
}
