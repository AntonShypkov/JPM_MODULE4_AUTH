package com.epam.advanced.java.api;

import com.epam.advanced.java.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/study/v1")
@RequiredArgsConstructor
public class InfoController {

    private final UsersService usersService;

    @GetMapping("/about")
    public String getAbout(){
        return "This is a JMP Study Spring MVC Application";
    }

    @GetMapping("/info")
    public String getInfo(){
        return "Spring Web Application";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "This is an Admin resource";
    }

    @GetMapping("/blockedUsers")
    public List<String> getBlockedUsers (){
        return usersService.getBlockedUsers();
    }
}
