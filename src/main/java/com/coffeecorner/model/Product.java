package com.coffeecorner.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Product {

    long id;

    String name;

    BigDecimal price;

    boolean addOn = false;

}
