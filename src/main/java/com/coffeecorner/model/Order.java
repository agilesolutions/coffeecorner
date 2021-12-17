package com.coffeecorner.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Order {

    long id;

    List<LineItem> lineItems;
}
