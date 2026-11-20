package com.example.securityDemo.controller;

import com.example.securityDemo.entity.Role;
import com.example.securityDemo.entity.User;
import com.example.securityDemo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    public ResponseEntity<String> addRole(@RequestBody Role role){
       roleService.addRole(role);
       return ResponseEntity.ok("done");

    }
}
