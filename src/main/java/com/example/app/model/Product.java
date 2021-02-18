package com.example.app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NonNull
    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @NonNull
    @Column(name = "prod_id_in_store")
    private int productIdInStore;

    @Column(name = "product_name")
    private String productName;
    @NonNull
    private Double price;

    @Column(name = "value")
    private String value;

    @Column(name = "code")
    private String code;

    public Product(@NonNull Store store, @NonNull int productIdInStore, String productName, @NonNull Double price,
                   String value, String code) {
        this.store = store;
        this.productIdInStore = productIdInStore;
        this.productName = productName;
        this.price = price;
        this.value = value;
        this.code = code;
    }

    public Product(@NonNull Store store, @NonNull int productIdInStore, String productName, @NonNull Double price) {
        this.store = store;
        this.productIdInStore = productIdInStore;
        this.productName = productName;
        this.price = price;
    }

}
