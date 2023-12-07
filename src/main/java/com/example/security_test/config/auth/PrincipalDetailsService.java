package com.example.security_test.config.auth;

import com.example.security_test.entity.UserEntity;
import com.example.security_test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Security Session => Authentication => UserDetails
    //


    @Override //로그인 요청과 함께 작동
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("유저네임은"+username);
        // 로그인 할때 입력한 ID를 DB에서 찾아본다
        UserEntity userEntity = userRepository.findByUsername(username);
        log.info("찾은 유저네임은"+userEntity);
        // 만약 찾은 Entity가 없으면 예외 발생
        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }
        return new PrincipalDetails(userEntity);
//        return new UserAdapter(userEntity);
    }
}
