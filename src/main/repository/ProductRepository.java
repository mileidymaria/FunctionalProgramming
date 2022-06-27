package main.repository;

import main.domain.model.Product;
import main.repository.impl.ProductRepositoryImpl;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    static ProductRepository getSoleInstance(){
        return ProductRepositoryImpl.getSoleInstance();
    }

    int getNextId();

    void insert(Product product);

    void remove(Product product);

    Iterable<Product> getAll();

    List<Product> getAscSortedList();

    List<Product> getDescSortedList();

    Optional<Product> findByName(String name);
}
