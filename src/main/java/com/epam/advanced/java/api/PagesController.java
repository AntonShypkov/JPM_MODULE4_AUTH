package com.epam.advanced.java.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

    @GetMapping
    public String getHome(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
