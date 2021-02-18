package com.example.app.controller;

import com.example.app.model.Product;
import com.example.app.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductAuchanController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/productAshan")
    public String product(Model model) {
        List<Product> products = new ArrayList<>(productRepo.findAll());
        products.removeIf(product -> product.getStore().getStoreId() == 5);
        model.addAttribute("productsAshan", products);

        return "productsAshan";
    }

    @PostMapping("/filterAshan")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<Product> products;

        if (filter != null && !filter.isEmpty()) {
            products = productRepo.findByProductNameContainingIgnoreCase(filter);
            products.removeIf(product -> product.getStore().getStoreId() == 5);
        } else {
            products = productRepo.findAll();
            products.removeIf(product -> product.getStore().getStoreId() == 5);
        }
        model.addAttribute("productsAshan", products);
        model.addAttribute("filterAshan", filter);
        return "filterAshan";
    }
}
