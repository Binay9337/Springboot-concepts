package com.example.securityDemo.service;

import com.example.securityDemo.entity.Role;
import com.example.securityDemo.entity.User;
import com.example.securityDemo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void addRole(Role role){
        roleRepository.save(role);

    }
}
