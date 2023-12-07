package com.example.security_test.dto;

import com.example.security_test.entity.UserEntity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Data //게터세터 및 다른 생성자 다 합친거
public class UserDto {

    @NotBlank // 비어있으면 안됨
    private String email;

    @NotBlank
    @Length(min = 3, max = 20) // 최소 3자 최대 20자
    private String username;

    @NotBlank
    @Length(min = 3, max = 20)
    private String password;

    private String role; // 유저 권한



//    public UserEntity toEntity() {
//
//        return new UserEntity(null , username, password, role, null);
//    }


    public UserEntity toEntity() { // 빌더 사용
        UserEntity userEntity = UserEntity.builder()
                .email(getEmail())
                .username(getUsername())
                .password(getPassword())
                .role(getRole())
                .build();

        return userEntity;
    }

}
