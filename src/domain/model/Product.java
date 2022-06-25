package domain.model;

import domain.enums.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product{
    private final int id;
    private String name;
    private String desc;
    private int quantity;
    private Category category;
    private final LocalDate creationDate;
    private double price;

    public Product(int id, String name, String desc, int quantity, Category category, LocalDate creationDate, double price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.category = category;
        this.creationDate = creationDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                ", creationDate=" + creationDate +
                ", price=" + price +
                '}';
    }
}
