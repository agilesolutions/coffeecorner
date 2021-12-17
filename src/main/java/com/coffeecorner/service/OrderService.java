package com.coffeecorner.service;

import com.coffeecorner.model.Order;
import com.coffeecorner.report.ReceiptWriter;
import com.coffeecorner.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

/**
 * Fetch one order instance from DB and process receipt.
 */
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ReceiptWriter receiptWriter;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.receiptWriter = new ReceiptWriter();
    }

    public void generateBill(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        receiptWriter.report(order);

    }


}
