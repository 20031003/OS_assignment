package com.example.security_test.service;


import com.example.security_test.dto.UserDto;
import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j

public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveNewUser(UserDto userDto) {
        log.info(userDto.toString());
        System.out.println(userDto);

        userDto.setRole("ROLE_USER"); //유저 권한을 USER로 한다

        // 엔티티로 변환
        UserEntity userEntity = userDto.toEntity();

        // 비밀번호 인코딩
        String rawPassword = userEntity.getPassword();
        // 1-1 passwordEncoder를 통해 rawPassword를 인코딩
        String encodePassword = passwordEncoder.encode(rawPassword);

        userEntity.setPassword(encodePassword); //인코딩된 비밀번호로 교체
        log.info(userEntity.toString());

        UserEntity newUser = userRepository.save(userEntity); // DB에 저장
        log.info("회원가입완료");
//        return newUser;
    }


}
