package com.example.app.model.pojo._5ka;

import lombok.Getter;

import java.util.ArrayList;

/**
 *    "next":"https://5ka.ru/api/v2/special_offers/?page=2&records_per_page=12&shopitem_category=&store=",
 *    "previous":null,
 *    "results":[
 *       {
 *          "id":30479,
 *          "name":"Изделия для праздников и карнавала, Хлопушка пружинная, Арт./ модель H1019",
 *          "mech":null,
 *          "img_link":"https://media.5ka.ru/media/products/3972295.jpg",
 *          "plu":3972295,
 *          "promo":{
 *             "id":1156808218,
 *             "date_begin":"2020-12-19",
 *             "date_end":"2021-01-17",
 *             "type":"",
 *             "description":"",
 *             "kind":"Z009",
 *             "expired_at":10
 *          },
 *          "current_prices":{
 *             "price_reg__min":24.99,
 *             "price_promo__min":7.99
 *          },
 *          "store_name":null
 *       },
 *       { ... },
 *       { ... },
 *       ]
 */

@Getter
public class Model5ka {
    private String next;
    private String previous;
    private ArrayList<Result> results = new ArrayList<>();
}

