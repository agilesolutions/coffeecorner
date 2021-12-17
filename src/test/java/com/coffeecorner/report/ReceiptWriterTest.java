package com.coffeecorner.report;

import com.coffeecorner.model.LineItem;
import com.coffeecorner.model.Order;
import com.coffeecorner.model.Product;
import com.coffeecorner.repository.OrderRepository;
import com.coffeecorner.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ReceiptWriterTest {

    @InjectMocks
    ReceiptWriter receiptWriter;

    @Test
    void given2ndBeverages_whenPrintingReceipt_thenOneExtraForFree(CapturedOutput capturedOutput) {

        receiptWriter.report(provideBonusOrder());

        assertAll("validating receipt entries"
            ,() -> assertEquals(4, capturedOutput.getAll().lines().count())
                ,() -> assertTrue(capturedOutput.getOut().contains("Coffee small - 2.5"))
                ,() -> assertTrue(capturedOutput.getOut().contains("One for free when ordering more than 3 beverages")));

    }

    @Test
    void givenOneBeverage_whenPrintingReceipt_thenNoExtrasFree(CapturedOutput capturedOutput) {

        receiptWriter.report(provideNonBonusOrder());

        assertAll("validating receipt entries"
                ,() -> assertEquals(2, capturedOutput.getAll().lines().count())
                ,() -> assertTrue(capturedOutput.getOut().contains("Coffee small - 2.5"))
                ,() -> assertFalse(capturedOutput.getOut().contains("One for free when ordering more than 3 beverages")));

    }

    private Order provideBonusOrder() {
        return Order.builder()
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
                .build();
    }

    private Order provideNonBonusOrder() {
        return Order.builder()
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
                .build();
    }
}