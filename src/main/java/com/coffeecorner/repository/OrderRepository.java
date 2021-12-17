package com.coffeecorner.repository;

import com.coffeecorner.model.LineItem;
import com.coffeecorner.model.Order;
import com.coffeecorner.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Any Springboot autoconfigured data storage goes here.
 */
@Repository
public class OrderRepository implements CrudRepository<Order, Long>{

    private final List<Order> orders = new ArrayList<>();

    public OrderRepository() {
        orders.add(Order.builder()
                .id(1)
                .lineItems(List.of(LineItem.builder()
                                .id(1)
                                .product(Product.builder()
                                        .id(1)
                                        .name("Coffee small")
                                        .price(BigDecimal.valueOf(2.50)).build())
                                .build()
                        , LineItem.builder()
                                .id(2)
                                .product(Product.builder()
                                        .id(1)
                                        .name("Bacon Role")
                                        .price(BigDecimal.valueOf(4.50)).build())
                                .build()
                        , LineItem.builder()
                                .id(3)
                                .product(Product.builder()
                                        .id(1)
                                        .name("Extra milk")
                                        .price(BigDecimal.valueOf(0.30)).build())
                                .build()
                ))
                .build());
    }


    @Override
    public Optional<Order> save(Order entity) {
            orders.add(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().filter(o -> o.getId()==id).findFirst();
    }

    @Override
    public Iterable<Order> findAll() {
        return orders;
    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
