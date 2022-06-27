package main.repository.impl;

import main.domain.model.Order;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class OrderRepositoryImpl {
    private static OrderRepositoryImpl soleInstance = null;
    private List<Order> poolOrders;

    private OrderRepositoryImpl() {
    }

    public static OrderRepositoryImpl getSoleInstance() {
        if(soleInstance == null){
            soleInstance = new OrderRepositoryImpl();
        }
        return soleInstance;
    }

    public long getNextId(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("Brazil/east")).toEpochMilli();
    }


}
