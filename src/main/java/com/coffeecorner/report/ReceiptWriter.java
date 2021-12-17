package com.coffeecorner.report;

import com.coffeecorner.model.LineItem;
import com.coffeecorner.model.Order;
import com.coffeecorner.model.Product;
import com.coffeecorner.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A bit awkward doing these peeks but it shows how you comfortably can travers, filter and process data, there is
 * of course more to this all.
 */
@Slf4j
public class ReceiptWriter implements ReportWriter<Order, StringBuilder>{

    @Override
    public void report(Order order) {

        long count = order.getLineItems().stream()
                .map(LineItem::getProduct)
                .peek(p -> System.out.printf("%s - %s\n",p.getName(),p.getPrice()))
                .filter(Predicate.not(Product::isAddOn))
                .count();
        // one for free when ordering 2 beverages
        if (count > 2) {
            System.out.printf("One for free when ordering more than %s beverages...\n",count);
        }

    }
}
