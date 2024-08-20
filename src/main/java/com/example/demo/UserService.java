package com.example.demo;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerNewUser(String username, String password) {
        // 加密密码
        String encodedPassword = passwordEncoder.encode(password);

        // 创建新用户并保存到数据库
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
    }
}
