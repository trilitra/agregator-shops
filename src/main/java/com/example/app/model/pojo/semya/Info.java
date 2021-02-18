package com.example.app.model.pojo.semya;


import lombok.Getter;

import java.util.List;

@Getter
public class Info {
        private Integer id;
        private String title;
        private List<Barcodes> barcodes;
        private String human_friendly_measure_with_value;
        private String human_friendly_measure_with_value_in_catalog;
}
