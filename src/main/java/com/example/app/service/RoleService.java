package com.example.app.service;

import com.example.app.model.Role;
import com.example.app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findById(Integer roleId) {
        return repository.findById(roleId).orElse(null);
    }


}