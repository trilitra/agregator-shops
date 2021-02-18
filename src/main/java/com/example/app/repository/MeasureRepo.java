package com.example.app.repository;

import com.example.app.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepo extends JpaRepository<Measure,Integer> {
}
