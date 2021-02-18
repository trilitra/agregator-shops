package com.example.app.service;

import com.example.app.repository.MeasureRepo;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {

    private final MeasureRepo repository;

    public MeasureService(MeasureRepo repository) {
        this.repository = repository;
    }
}