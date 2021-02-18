package com.example.app.service;

import com.example.app.repository.StoreRepo;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepo repository;

    public StoreService(StoreRepo repository) {
        this.repository = repository;
    }
}