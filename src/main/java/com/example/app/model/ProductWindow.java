package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product_window")
public class ProductWindow {

    @Id
    @Column(name = "product_window_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productWindowId;

    @ManyToOne
    @JoinColumn(name = "meas_id")
    private Measure measure;

    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();
}
