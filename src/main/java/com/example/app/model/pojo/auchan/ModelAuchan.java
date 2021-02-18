package com.example.app.model.pojo.auchan;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * "items": [
 *      {
 *       "id": 3050377,
 *       "code": "kolbasa-livernaya-cherkizovo-ukrainskaya-s-pechenyu-400-g",
 *       "modifications": [],
 *       "categoryCodes": [
 *         {
 *           "id": 561,
 *           "name": "Колбасные изделия",
 *           "code": "kolbasnye-izdeliya"
 *         }, ...
 *       ],
 *       "additionalCategories": [],
 *       "title": "Колбаса ливерная «Черкизово» Украинская с печенью, 400 г",
 *       "vendorCode": "4607089698126",
 *       "price": {
 *         "value": 75.99,
 *         "currency": "RUB",
 *         "per": "item"
 *       },
 *       "stock": {
 *         "qty": 12,
 *         "storeId": 87
 *       },
 *       "basketStep": 1,
 *       "saleActive": true,
 *       "active": true,
 *       "isAdult": false,
 *       "oldPrice": null,
 *       "discount": null,
 *       "catalogImageId": 2455954,
 *       "media": [
 *         2455954
 *       ],
 *       "description": {
 *         "content": "Ливерная колбаса приготовлена по классическому рецепту из печени и свинины."
 *       },
 *       "characteristics": [
 *         {
 *           "id": 1,
 *           "title": "Масса нетто, кг",
 *           "value": "0.4"
 *         }, ...
 *       ],
 *       "brand": {
 *         "name": "Черкизово",
 *         "code": "cherkizovo"
 *       },
 *       "gimaId": "473581",
 *       "storageAddress": "277"
 *     }, ...
 *   ],
 * "range": 200,
 * "minPrice": 29.98,
 * "maxPrice": 1618.99
 */

@Getter
public class ModelAuchan {
    private ArrayList<Item> items = new ArrayList<>();
    private Integer range;
    private Double minPrice;
    private Double maxPrice;
}
