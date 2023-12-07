package com.example.security_test.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class IndexController {
    @GetMapping("/")
    public String index(Principal principal, Model model) {
        if (principal != null) {
            String userName = principal.getName();
            UserEntity User = userRepository.findByUsername(userName);
            if (User != null) {
                model.addAttribute("userEntity", User);
            }
        }
        return "index";
    }

}
