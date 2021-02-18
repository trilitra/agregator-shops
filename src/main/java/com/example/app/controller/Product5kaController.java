package com.example.app.controller;

import com.example.app.model.ProductWindow;
import com.example.app.repository.MeasureRepo;
import com.example.app.repository.StoreRepo;
import com.example.app.service.ProductService;
import com.example.app.service.store._5ka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/products")
public class Product5kaController {

    @Autowired
    MeasureRepo measureRepo;
    @Autowired
    StoreRepo storeRepo;

    @GetMapping("/promo/5ka")
    public ModelAndView getProductsForPromo() {
        _5ka handler = new _5ka(measureRepo, storeRepo);
        List<ProductWindow> productsForPromo = handler.getProductsForPromo();
        ProductService productService = new ProductService();
        productService.workWithProduct();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products_5ka_for_promo", productsForPromo);

        return null;
    }

}