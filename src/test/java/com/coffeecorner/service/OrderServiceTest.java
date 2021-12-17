package com.coffeecorner.service;

import com.coffeecorner.model.LineItem;
import com.coffeecorner.model.Order;
import com.coffeecorner.model.Product;
import com.coffeecorner.report.ReceiptWriter;
import com.coffeecorner.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ReceiptWriter receiptWriter;

    @InjectMocks
    OrderService orderService;

    @Test
    void givenOrderNotExist_whenPrintingReceipt_thenRaiseException() {

        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.generateBill(any()),"Order not found");

    }


    @Test
    void givenOrderExist_whenPrintingReceipt_thenProcessReceipt() {

        when(orderRepository.findById(any())).thenReturn(provideOrder());

        orderService.generateBill(any());

        verify(receiptWriter, times(1)).report(any(Order.class));

    }

    private Optional<Order> provideOrder() {
        return Optional.of(Order.builder()
                .id(1)
                .lineItems(List.of(LineItem.builder()
                                .id(1)
                                .product(Product.builder()
                                        .id(1)
                                        .name("Coffee small")
                                        .price(BigDecimal.valueOf(2.50)).build())
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
}