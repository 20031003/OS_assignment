package com.example.security_test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {

    @Id // 정보의 id값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 1, 2, 3, 4...
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;

    @Column
    private String role;

    private String emailCheckToken;

    @CreationTimestamp
    private Timestamp createDate;

    private boolean emailVerified;

    private String profileImage;

    private String bio;


    public void emailCheck() {
        this.emailVerified = true;
    }

    public void generateToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }

    public boolean canSendEmail() {
        return this.createDate.toLocalDateTime().isBefore(LocalDateTime.now().minusHours(1));

    }
}