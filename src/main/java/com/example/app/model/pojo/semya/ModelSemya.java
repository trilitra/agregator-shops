package com.example.app.model.pojo.semya;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *     {
 *         "id": 1914,
 *         "info": {
 *             "id": 1914,
 *             "title": "Ветчина Для завтрака Кунгурский мясокомбинат (весовая)",
 *             "manufacture": {
 *                 "id": 22,
 *                 "title": "ООО \"Мясокомбинат Кунгурский\""
 *             },
 *             "brand": {
 *                 "id": 340,
 *                 "title": "Кунгурский МК"
 *             },
 *             "country": {
 *                 "id": 3,
 *                 "title": "Россия"
 *             },
 *             "image_link": "https://domoidostavim.ru/images/static/products/e5682e06-4101-4cf0-915c-57972db49d31.jpg",
 *             "thumb_link": "https://domoidostavim.ru/images/static/products/thumb_e5682e06-4101-4cf0-915c-57972db49d31.jpg",
 *             "data": [],
 *             "is_weight": true,
 *             "weight": 0,
 *             "volume": null,
 *             "percent": null,
 *             "product_weight_range": {
 *                 "id": 290,
 *                 "product_id": 1914,
 *                 "weight_type_id": 1,
 *                 "step": 100,
 *                 "min_weight": 100,
 *                 "max_weight": 3000
 *             },
 *             "images": [
 *                 {
 *                     "id": 5284,
 *                     "product_id": 1914,
 *                     "image": {
 *                         "url": "/images/static/products/e5682e06-4101-4cf0-915c-57972db49d31.jpg",
 *                         "thumb": {
 *                             "url": "/images/static/products/thumb_e5682e06-4101-4cf0-915c-57972db49d31.jpg"
 *                         },
 *                         "original": {
 *                             "url": "/images/static/products/original_e5682e06-4101-4cf0-915c-57972db49d31.jpg"
 *                         }
 *                     },
 *                     "active": true,
 *                     "ord": 1,
 *                     "shop_id": null,
 *                     "is_additional": false,
 *                     "image_link": "https://domoidostavim.ru/images/static/products/e5682e06-4101-4cf0-915c-57972db49d31.jpg",
 *                     "thumb_link": "https://domoidostavim.ru/images/static/products/thumb_e5682e06-4101-4cf0-915c-57972db49d31.jpg"
 *                 }
 *             ],
 *             "barcodes": [
 *                 {
 *                     "id": 987,
 *                     "product_id": 1914,
 *                     "code": "2291614",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.084+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 988,
 *                     "product_id": 1914,
 *                     "code": "2545019",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.106+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 984,
 *                     "product_id": 1914,
 *                     "code": "72720",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.150+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 985,
 *                     "product_id": 1914,
 *                     "code": "4607053870657",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.162+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 986,
 *                     "product_id": 1914,
 *                     "code": "2217418",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.181+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 989,
 *                     "product_id": 1914,
 *                     "code": "4607053873849",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": null,
 *                     "updated_at": "2019-10-16T20:12:15.130+05:00",
 *                     "chain_store_product_id": 3058
 *                 },
 *                 {
 *                     "id": 1121254,
 *                     "product_id": 1914,
 *                     "code": "2207384",
 *                     "archive": false,
 *                     "type_id": 1,
 *                     "shop_id": 1,
 *                     "product_shop_id": 1914,
 *                     "created_at": "2020-02-19T05:35:51.513+05:00",
 *                     "updated_at": "2020-03-05T14:00:25.986+05:00",
 *                     "chain_store_product_id": 3058
 *                 }
 *             ],
 *             "human_friendly_measure_with_value": "100 - 3000 г.",
 *             "human_friendly_measure_with_value_in_catalog": "за 1 кг."
 *         },
 *         "price": "649.0",
 *         "is_discount": false,
 *         "cart": null,
 *         "favorite": null,
 *         "is_present": false,
 *         "prev_price": "649.0",
 *         "stars": 17,
 *         "cart_amount": 0,
 *         "cart_weight": 0,
 *         "has_mood_badges": null,
 *         "badge": null,
 *         "first_two_active_badges": null,
 *         "tags": [
 *             {
 *                 "id": 1,
 *                 "name": "В нарезку",
 *                 "taggings_count": 406
 *             }
 *         ],
 *         "badges": null,
 *         "alcohol": false,
 *         "is_show_filters": true,
 *         "shop": {
 *             "id": 1,
 *             "slug": "semya"
 *         }
 *     }
 */

@Getter
    public class ModelSemya {
    private Integer id;
    private Info info;
    private Double price;
    }





