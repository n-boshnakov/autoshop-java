package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.data.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex(Model model, Authentication authentication) {
        final String welcomeMessage = "Welcome to Mechanic Autos!";
        model.addAttribute("welcome", welcomeMessage);

        if(authentication != null){
            User principal = (User) authentication.getPrincipal();
            model.addAttribute("username", principal.getUsername());
            return "index";
        }
        else{
            return "login";
        }
    }

    @GetMapping("login")
    public String login(Model model) {
        final String welcomeMessage = "Welcome to Mechanic Autos!";
        model.addAttribute("welcome", welcomeMessage);
        return "login";
    }

    @GetMapping("unauthorized")
    public String unauthorized(Model model) {
        final String welcomeMessage = "Welcome to Mechanic Autos!";
        model.addAttribute("welcome", welcomeMessage);
        return "unauthorized";
    }
}
