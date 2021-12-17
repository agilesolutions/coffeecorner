package com.coffeecorner.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LineItem {

    long id;

    Product product;

    int count;
    
}
