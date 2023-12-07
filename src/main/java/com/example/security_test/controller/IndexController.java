package com.example.security_test.controller;

import com.example.security_test.dto.UserDto;
import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import com.example.security_test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;
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

    @GetMapping("/loginForm")
    public String loginForm() {

        return "loginForm";
    }

    @GetMapping("/signUpForm") //회원가입 데이터를 받는 폼
    public String signUpForm(Model model) {
        model.addAttribute("userDto", new UserDto());

        return "signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            return "signUpForm"; //에러가 나타나면 페이지 리턴
        }

        userService.saveNewUser(userDto); //userDto로 들어온 데이터로 새로운 유저를 만드는 과정

        return "redirect:/loginForm";
    }

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, Principal principal){

        UserEntity userNickname = userRepository.findByUsername(nickname);
        if(userNickname == null){
            throw  new IllegalArgumentException(nickname + "에 해당되는 사용자가 없습니다.");
        }

        model.addAttribute("userEntity", userNickname);
        model.addAttribute("isOwner", userNickname.getUsername().equals(principal.getName()));

        log.info(userNickname.getUsername());
        log.info(principal.getName());

        return "profile";
    }


}
