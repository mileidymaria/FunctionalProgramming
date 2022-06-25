package service;

import domain.model.Order;

import java.util.Collection;


public class BuyService {
    private static BuyService soleInstance = null;
    private Collection<Order> poolFinishedOrders;
    private Collection<Order> poolNonFinishedOrders;

    public BuyService() {
    }

    public static BuyService getSoleInstance() {
        if(soleInstance == null){
            soleInstance = new BuyService();
        }
        return soleInstance;
    }

    public boolean finishOrder(Order order){

        return true;
    }
}
