package com.example.app.controller;

import com.example.app.model.Product;
import com.example.app.model.Store;
import com.example.app.model.User;
import com.example.app.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class BasketController {

    @Autowired
    private ProductRepo productRepo;

    private final HashMap<String, Object> params = new HashMap<>();

    private final HashMap<Integer, List<Product>> spisok = new HashMap<>();

    private List<Product> products = new ArrayList<>();


    @GetMapping("/basket")
    public ModelAndView getBasket(@AuthenticationPrincipal User user) {
        List<Product> products = spisok.get(user.getId());

        if (products == null) {
            params.put("basket", new ArrayList<>());
            return new ModelAndView("basket", params);
        } else {
            params.put("basket", spisok.get(user.getId()));
            return new ModelAndView("basket", params);
        }
    }

    @PostMapping("/addInBasketAshan")
    public ModelAndView addInBasketAshan(@RequestParam Integer productId, @AuthenticationPrincipal User user) {
        List<Product> products = new ArrayList<>();
        if (spisok.get(user.getId()) != null) {
            products.addAll(spisok.get(user.getId()));
        }
        products.add(productRepo.findProductByProductId(productId));
        spisok.put(user.getId(), products);
        return new ModelAndView("redirect:/productAshan");
    }

    @PostMapping("/addInBasketSemya")
    public ModelAndView addInBasketSemya(@RequestParam Integer productId, @AuthenticationPrincipal User user) {
        List<Product> products = new ArrayList<>();
        if (spisok.get(user.getId()) != null) {
            products.addAll(spisok.get(user.getId()));
        }
        products.add(productRepo.findProductByProductId(productId));
        spisok.put(user.getId(), products);
        return new ModelAndView("redirect:/productSemya");
    }

    @PostMapping("/deleteInBasket")
    public ModelAndView deleteInBasket(@RequestParam Integer productId, @AuthenticationPrincipal User user) {
        spisok.get(user.getId()).remove(productRepo.findProductByProductId(productId));
        return new ModelAndView("redirect:/basket");
    }

    @PostMapping("/summa")
    public ModelAndView summaAshan(@AuthenticationPrincipal User user) {
        List<Product> products = spisok.get(user.getId());

        if (products == null) {
            params.put("basket", new ArrayList<>());
            return new ModelAndView("basket", params);
        }

        List<Product> productsAshan = new ArrayList<>();
        List<Product> productsSemya = new ArrayList<>();
        Double summaAshan = 0.0;
        Double summaSemya = 0.0;

        for (Product product : products) {
            String code = product.getCode();

            boolean AshanOrSemya = (product.getStore().getStoreId() == 4);

            if (AshanOrSemya) {
                productsAshan.add(product);
                List<Product> productNew = productRepo.findAProductByCodeContains(code);
                for (Product productFind : productNew) {

                    if ((productFind.getStore().getStoreId() == 5) && (!productsSemya.contains(productFind))) {
                        productsSemya.add(productFind);

                    }
                }
            }

            if (!AshanOrSemya) {
                productsSemya.add(product);
                List<Product> productNew = productRepo.findAProductByCodeContains(code);
                for (Product productFind : productNew) {

                    if ((productFind.getStore().getStoreId() == 4) && (!productsAshan.contains(productFind))) {
                        productsAshan.add(productFind);

                    }
                }
            }
        }
        params.put("productsAshan", productsAshan);
        params.put("productsSemya", productsSemya);

        for (Product product : productsAshan) {
            summaAshan += product.getPrice();
        }

        for (Product product : productsSemya) {
            summaSemya += product.getPrice();
        }
        params.put("summaAshan", summaAshan);
        params.put("summaSemya", summaSemya);
        return new ModelAndView("summa", params);
    }

}
