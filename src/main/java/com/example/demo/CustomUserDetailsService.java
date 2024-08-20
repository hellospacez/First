package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 使用BCrypt加密密码
       // String encodedPassword = passwordEncoder.encode("password");

        // 从数据库中加载用户
        com.example.demo.model.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // 将 UserEntity 转换为 Spring Security 的 UserDetails 对象
        return new User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }
}
