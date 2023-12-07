package com.example.security_test.controller;

import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserEntity userEntity;

    private final UserRepository userRepository;


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
