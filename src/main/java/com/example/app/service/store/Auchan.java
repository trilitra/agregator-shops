package com.example.app.service.store;

import com.example.app.model.Measure;
import com.example.app.model.Product;
import com.example.app.model.ProductWindow;
import com.example.app.model.Store;
import com.example.app.model.pojo.auchan.Item;
import com.example.app.model.pojo.auchan.ModelAuchan;
import com.example.app.repository.MeasureRepo;
import com.example.app.repository.ProductRepo;
import com.example.app.repository.StoreRepo;
import com.example.app.service.CustomHttpRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Auchan {

    private final String baseHostName = "https://www.auchan.ru/v1/";
    private final Charset charset = StandardCharsets.UTF_8;
    private CustomHttpRequest request;
    private ModelAuchan modelAuchan;
    private int recordsPerPage = 50;
    private int page = 3;
    private int merchantId = 727;
    private String category = "sladkie-gazirovannye-napitki-i-limonady-1";
    private String orderField = "rank";
    private String orderDirection = "asc";

    private final MeasureRepo measureRepo;
    private final StoreRepo storeRepo;
    private final ProductRepo productRepo;

    @Autowired
    public Auchan(MeasureRepo measureRepo, StoreRepo storeRepo, ProductRepo productRepo) {
        this.measureRepo = measureRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    public List<ProductWindow> getProductsByCategories() {


        List<ProductWindow> productWindowList = new ArrayList<>();
        ProductWindow productWindow;
        Product product;

        String store = "Ашан";
        List<Store> storeList = storeRepo.findAll();

        List<Measure> measureList = measureRepo.findAll();
        String codeStoreMeasure = "-";


        for (int i = 0; i < page ; i++) {

            sendRequest(baseHostName +
                    "catalog/products?merchantId=" + merchantId +
                    "&filter[category]=" + category +
                    "&page=" + i +
                    "&perPage=" + recordsPerPage +
                    "&orderField=" + orderField +
                    "&orderDirection=" + orderDirection);


            modelAuchan = parseJson();

            for (Item item : modelAuchan.getItems()) {

                String str = item.getTitle();
                ArrayList<String> list = new ArrayList<>();

                for (String retval : str.split(", ")) {
                    list.add(retval);
                }
                if(list.size()<2){
                    list.add("Нет данных");
                }

                codeStoreMeasure = item.getPrice().getPer();
                store = item.getStorageAddress() == null || item.getStorageAddress() == 0
                        ? store : String.valueOf(item.getStorageAddress());

                product = new Product(
                        getStore(storeList, store),
                        item.getId(),
                        list.get(0),
                        item.getPrice().getValue(),
                        adapterValue(list.get(1)),
                        item.getVendorCode());

                productWindow = new ProductWindow();
                productWindow.setMeasure(getMeasure(measureList, codeStoreMeasure));
                productWindow.setName(item.getTitle());
                productWindow.setDescription(item.getDescription().getContent());
                productWindow.getProducts().add(product);
                productWindowList.add(productWindow);
            }
        }
        log.info("Получение продуктов Ашана по категории " + category);
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


    private ModelAuchan parseJson() {
        Gson gson = new Gson();
        return gson.fromJson(request.getResponseBody(), ModelAuchan.class);
    }

    private void sendRequest(String hostName) {
        request = new CustomHttpRequest(hostName, charset);
        request.sendGetRequest();
    }

    private String adapterValue(String s) {
        if(s.equals("1,25 л")){
            return "1.25 л.";
        }
        if(s.equals("0,9 л")){
            return "0.9 л.";
        }
        if(s.equals("1,5 л")){
            return "1.5 л.";
        }
        if(s.equals("330 мл")){
            return "0.33 л.";
        }
        if(s.equals("500 мл")){
            return "0.5 л.";
        }
        if(s.equals("900 мл")){
            return "0.9 л.";
        }
        if(s.equals("1 л")){
            return "1.0 л.";
        }
        if(s.equals("750 мл")){
            return "0.75 л.";
        }
        if(s.equals("2 л")){
            return "2.0 л.";
        }
        if(s.equals("0,175 л")){
            return "0.175 л.";
        }
        if(s.equals("600 мл")){
            return "0.6 л.";
        }
        if(s.equals("1,2 л")){
            return "1.2 л.";
        }
        if(s.equals("250 мл")){
            return "0.25 л.";
        }
        if(s.equals("450 мл")){
            return "0.45 л.";
        }
        if(s.equals("Нет данных")){
            return "0.5 л.";
        }
        if(s.equals("345 мл")){
            return "0.345 л.";
        }
        if(s.equals("175 мл")){
            return "0.175 л.";
        }
        return s;
    }


}
