package com.example.securityDemo.controller;

import com.example.securityDemo.dto.UerResisterResponse;
import com.example.securityDemo.dto.UserRegisterRequest;
import com.example.securityDemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/hello")
    public String ayHello(){
        return "hello";
    }

    @PostMapping("/reg")
    public ResponseEntity<UerResisterResponse> register(
            @RequestBody UserRegisterRequest registerRequest) {

        UerResisterResponse register =
                authService.register(registerRequest);

        return ResponseEntity.ok(register);
    }
//    @GetMapping("/login")
//    public ResponseEntity<Boolean> login(@RequestBody UserRegisterRequest registerRequest){
//
//        Boolean login = authService.login(registerRequest);
//
//        return ResponseEntity.ok(login);
//    }

    public CsrfToken getToken(CsrfToken csrfToken ){
        return csrfToken;
    }
}
