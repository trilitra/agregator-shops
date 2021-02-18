package com.example.app.service.store;

import com.example.app.model.Measure;
import com.example.app.model.Product;
import com.example.app.model.ProductWindow;
import com.example.app.model.Store;
import com.example.app.model.pojo.semya.Barcodes;
import com.example.app.model.pojo.semya.Info;
import com.example.app.model.pojo.semya.ModelSemya;
import com.example.app.repository.MeasureRepo;
import com.example.app.repository.ProductRepo;
import com.example.app.repository.StoreRepo;
import com.example.app.service.CustomHttpRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Semya {

    private final String baseHostName = "https://domoidostavim.ru/semya/categories/";
    private final Charset charset = StandardCharsets.UTF_8;
    Map<String, String> requestHeaders = new HashMap<>();
    private CustomHttpRequest request;
    private ModelSemya modelSemya;
    private int page = 5;
    private String category = "gazirovannye-napitki";


    private final MeasureRepo measureRepo;
    private final StoreRepo storeRepo;
    private final ProductRepo productRepo;

    @Autowired
    public Semya(MeasureRepo measureRepo, StoreRepo storeRepo, ProductRepo productRepo) {
        this.measureRepo = measureRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    public List<ProductWindow> getProductsByCategories() {
        requestHeaders.put("Content-Type", "application/json; charset=utf-8");
        requestHeaders.put("Accept", "application/json, text/javascript, */*; q=0,01");
        requestHeaders.put("X-Requested-With", "XMLHttpRequest");
        List<ProductWindow> productWindowList = new ArrayList<>();
        ProductWindow productWindow;
        Product product;
        String store = "Семья";
        List<Store> storeList = storeRepo.findAll();

        List<Measure> measureList = measureRepo.findAll();
        String codeStoreMeasure = "-";

        for (int i = 1; i < page; i++) {


            sendRequest(baseHostName + category +
                    "?sort=asc&column=rate&page=" + i);

            List<ModelSemya> modelSemya = parseJson();

            for (ModelSemya modelSemya1 : modelSemya) {
                Info info = modelSemya1.getInfo();
                codeStoreMeasure = info.getHuman_friendly_measure_with_value_in_catalog();

                List<Barcodes> barcodes = info.getBarcodes();


                product = new Product(
                        getStore(storeList, store),
                        info.getId(),
                        info.getTitle(),
                        modelSemya1.getPrice(),
                        info.getHuman_friendly_measure_with_value(),
                        barcodes.get(0).getCode());

                productWindow = new ProductWindow();
                productWindow.setMeasure(getMeasure(measureList, codeStoreMeasure));
                productWindow.setName(info.getTitle());
                productWindow.setDescription(info.getHuman_friendly_measure_with_value());
                productWindow.getProducts().add(product);
                productWindowList.add(productWindow);
            }
        }
        return productWindowList;

    }

    private Measure getMeasure(List<Measure> measureList, String codeStoreMeasure) {
        Optional<Measure> measure = measureList.stream()
                .filter(v -> v.getStoreMeasure().equals(codeStoreMeasure)).findFirst();

        return measure.orElseGet(() -> measureList.stream()
                .filter(v -> "-".equals(v.getStoreMeasure())).findFirst().get());
    }

    private Store getStore(List<Store> storeList, String storeName) {
        Optional<Store> store = storeList.stream()
                .filter(v -> v.getName().equals(storeName)).findFirst();

        return store.orElseGet(() -> storeList.stream()
                .filter(v -> "Ашан".equals(v.getName())).findFirst().get());
    }

    private List<ModelSemya> parseJson() {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(request.getResponseBody(), ModelSemya[].class));
    }

    private void sendRequest(String hostName) {
        request = new CustomHttpRequest(hostName, charset, requestHeaders);
        request.sendGetRequest();
    }

}


