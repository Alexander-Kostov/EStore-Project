package com.example.EStore.util;

import com.example.EStore.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderActionSchedule {

    private OrderService orderService;

    public OrderActionSchedule(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void changeAction() {
        this.orderService.changeAllActions();
    }
}
