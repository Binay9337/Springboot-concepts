package com.example.securityDemo.service;

import com.example.securityDemo.dto.UerResisterResponse;
import com.example.securityDemo.dto.UserRegisterRequest;
import com.example.securityDemo.entity.Role;
import com.example.securityDemo.entity.User;
import com.example.securityDemo.repository.RoleRepository;
import com.example.securityDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UerResisterResponse register(UserRegisterRequest registerRequest) {

        Logger log = Logger.getLogger("AuthService.class");
        log.info("is it reaching or not");

        User user = new User();
        user.setUserName(registerRequest.getUsername());

        String encode = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encode);
        user.setEnabled(true);
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRole().add(role);
        userRepository.save(user);

        UerResisterResponse uerResisterResponse = new UerResisterResponse();
        uerResisterResponse.setUserName(user.getUserName());
        return uerResisterResponse;
    }

//    public boolean login(UserRegisterRequest registerRequest){
//
//        Optional<User> userOptional = userRepository.findByUserName(registerRequest.getUserName());
//
//       if (userOptional.isEmpty()){
//          return false;
//          }
//
//       User user = userOptional.get();
//
//      return passwordEncoder.matches(registerRequest.getPassword(),user.getPassword());
//    }


}
