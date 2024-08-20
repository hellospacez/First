package com.example.demo.controller;

import com.example.demo.model.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/hello1")
    public String hello1() {
        return "hell123213o";
    }

    @RequestMapping("/api/v1/users")
    public class UserControllerV1 {
        @GetMapping
        public ResponseEntity<User> getUser() {
            // V1版本的实现
            return ResponseEntity.ok(new User());
        }
    }

    @RequestMapping("/api/v2/users")
    public class UserControllerV2 {
        @GetMapping
        public ResponseEntity<User> getUser() {
            // V2版本的实现
            return ResponseEntity.ok(new User());
        }
    }
}