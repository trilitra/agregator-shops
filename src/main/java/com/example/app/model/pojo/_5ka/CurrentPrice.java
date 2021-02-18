package com.example.app.model.pojo._5ka;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class CurrentPrice {
    @NonNull
    private Double price_reg__min;
    private Double price_promo__min;
}
