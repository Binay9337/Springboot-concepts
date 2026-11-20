package com.example.securityDemo.controller;

import com.example.securityDemo.dto.LoginRequest;
import com.example.securityDemo.dto.LoginResponse;
import com.example.securityDemo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    private LoginResponse login(@RequestBody LoginRequest loginRequest){

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUserName(),loginRequest.getPassword());

//    or
//        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());

        Authentication authenticationPrincipal =  authenticationManager.authenticate(authenticationRequest);

String token = jwtService.generateToken(authenticationPrincipal);

return  new LoginResponse(token);




    }
}
