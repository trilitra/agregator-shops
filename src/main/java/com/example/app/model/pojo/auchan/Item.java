package com.example.app.model.pojo.auchan;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Item {
    private Integer id;
    private String code;
    private ArrayList<CategoryCodes> categoryCodes = new ArrayList<>();
    private String title;
    private String vendorCode;
    private Price price;
    private Description description;
    private ArrayList<Characteristics> characteristics = new ArrayList<>();
    private Brand brand;
    private Integer gimaId;
    private Integer storageAddress;
}
