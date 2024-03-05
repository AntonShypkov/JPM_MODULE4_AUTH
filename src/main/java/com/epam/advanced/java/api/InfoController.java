package com.epam.advanced.java.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study/v1")
public class InfoController {

    @GetMapping("/info")
    public String getInfo(){
        return "Spring Web Application";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "This is a JMP Study Spring MVC Application";
    }
}
