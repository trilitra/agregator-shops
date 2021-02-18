package com.example.app.service.store;

import com.example.app.model.Measure;
import com.example.app.model.Product;
import com.example.app.model.ProductWindow;
import com.example.app.model.Store;
import com.example.app.model.pojo._5ka.Model5ka;
import com.example.app.model.pojo._5ka.Result;
import com.example.app.repository.MeasureRepo;
import com.example.app.repository.StoreRepo;
import com.example.app.service.CustomHttpRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class _5ka {

    private final String baseHostName = "https://5ka.ru/api/v2/";
    private final Charset charset = StandardCharsets.UTF_8;
    private CustomHttpRequest request;
    private Model5ka _5kaModel;
    private int recordsPerPage = 50;
    private int page = 5;

    private final MeasureRepo measureRepo;
    private final StoreRepo storeRepo;

    @Autowired
    public _5ka(MeasureRepo measureRepo, StoreRepo storeRepo) {
        this.measureRepo = measureRepo;
        this.storeRepo = storeRepo;
    }

    public List<ProductWindow> getProductsForPromo() {
        sendRequest(baseHostName + "special_offers/?store=" +
                "&records_per_page=" + recordsPerPage +
                "&page=" + page);

        _5kaModel = parseJson();

        List<ProductWindow> productWindowList = new ArrayList<>();
        ProductWindow productWindow;
        Product product;

        String store = "5ка";
        List<Store> storeList = storeRepo.findAll();

        List<Measure> measureList = measureRepo.findAll();
        String codeStoreMeasure = "-";

        for (Result result : _5kaModel.getResults()) {
            store = result.getStore_name() == null ? store : result.getStore_name();

            product = new Product(
                    getStore(storeList, store),
                    result.getId(),
                    result.getName(),
                    result.getCurrent_prices().getPrice_reg__min());

            productWindow = new ProductWindow();
            productWindow.setMeasure(getMeasure(measureList, codeStoreMeasure));
            productWindow.setName(result.getName());
            productWindow.setDescription(result.getPromo().getDescription());
            productWindow.getProducts().add(product);
            productWindowList.add(productWindow);

        }
        log.info("Получение продуктов 5ки по акции");
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
                .filter(v -> "Пятерочка".equals(v.getName())).findFirst().get());
    }

    private Model5ka parseJson() {
        Gson gson = new Gson();
        return gson.fromJson(request.getResponseBody(), Model5ka.class);
    }

    private void sendRequest(String hostName) {
        request = new CustomHttpRequest(hostName, charset);
        request.sendGetRequest();
    }

}
