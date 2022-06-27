package main.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private long id;
    private Map<Product, Integer> products = new HashMap<>(10);
    private double total;

    public Order(long id, Map<Product, Integer> products, double total) {
        this.id = id;
        this.products = products;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public double getTotal() {
        return total;
    }
}
