package com.coffeecorner;

import com.coffeecorner.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CoffeeCorner implements CommandLineRunner {

    private final OrderService orderService;

    public static void main(String[] args) {

        SpringApplication.run(CoffeeCorner.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        orderService.generateBill(1L);

    }
}
