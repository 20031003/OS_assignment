package com.example.security_test.config.auth;

import com.example.security_test.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@ToString
public class PrincipalDetails implements UserDetails {


    private UserEntity userEntity;

    public PrincipalDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    @Override //권한 설정
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정에 락이 안걸렸는지?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 만료 안되었는지?
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화 되었는지?

        return true;
    }


}
