package com.example.app.service;

import com.example.app.model.*;
import com.example.app.repository.*;
import com.example.app.service.store.Auchan;
import com.example.app.service.store.Semya;
import com.example.app.service.store._5ka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private MeasureRepo measureRepo;



    @Scheduled(fixedRate = 86400000)
    public void workWithProduct() {
        log.info("Запуск джоба по работе с продуктами!");
      //  getStartedWith5kaProducts();
        getStartedWithAuchanProducts();
        getStartedWithSemyaProducts();

        log.info("Конец работы джоба с продуктами!");
    }

    private void getStartedWithSemyaProducts() {
        Semya semya = new Semya(measureRepo, storeRepo, productRepo);
        List<ProductWindow> productWindowList = semya.getProductsByCategories();
        deleteAllFromProduct(5);
        insertInProduct(productWindowList);
    }


    private void getStartedWithAuchanProducts() {
        Auchan auchan = new Auchan(measureRepo, storeRepo, productRepo);
        List<ProductWindow> productWindowList = auchan.getProductsByCategories();
        deleteAllFromProduct(4);
        insertInProduct(productWindowList);
    }

    private void getStartedWith5kaProducts() {
        _5ka store5k = new _5ka(measureRepo, storeRepo);
        List<ProductWindow> productWindowList = store5k.getProductsForPromo();
        deleteAllFromProduct(1);
        insertInProduct(productWindowList);
    }

    public void insertInProduct(List<ProductWindow> productWindowList) {
        log.info("Вставка продуков: %d", productWindowList.size());
        if (productWindowList.isEmpty()) {
            return;
        }
        Store store = productWindowList.get(0).getProducts().get(0).getStore();
        for (ProductWindow productWindow : productWindowList) {
            List<Product> products = productWindow.getProducts();
            storeRepo.save(store);
            products.stream().peek(p -> p.setValue(p.getValue())).forEach(productRepo::save);
            products.stream().peek(p -> p.setCode(p.getCode())).forEach(productRepo::save);
            products.stream().peek(p -> p.setStore(store)).forEach(productRepo::save);
        }
    }

    public void deleteAllFromProduct(Integer storeId) {
        log.info("Удаление продуктов магазина: %s", storeRepo.findStoreByStoreId(storeId).getName());
        List<Product> list = productRepo.findAll();
        for (Product product : list) {
            if (product.getStore().getStoreId().equals(storeId)) {
                productRepo.delete(product);
            }
        }
    }
}
