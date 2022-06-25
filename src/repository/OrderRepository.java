package repository;

import domain.model.Order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class OrderRepository {
    private static OrderRepository soleInstance = null;
    private List<Order> poolOrders;

    private OrderRepository() {
    }

    public static OrderRepository getSoleInstance() {
        if(soleInstance == null){
            soleInstance = new OrderRepository();
        }
        return soleInstance;
    }

    public long getNextId(){
        return LocalDateTime.now().toInstant(ZoneOffset.of("Brazil/east")).toEpochMilli();
    }


}
