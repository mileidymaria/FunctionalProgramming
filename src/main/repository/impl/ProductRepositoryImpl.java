package main.repository.impl;

import main.domain.model.Product;
import main.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {
    private static ProductRepositoryImpl soleInstance = null;
    private List<Product> poolProducts;

    private ProductRepositoryImpl() {
    }

    @Override
    public int getNextId(){
        if(poolProducts == null){
            return 1;
        }
        return poolProducts.size() + 1;
    }

    public static ProductRepositoryImpl getSoleInstance(){
        if(soleInstance == null){
           soleInstance = new ProductRepositoryImpl();
        }
        return soleInstance;
    }

    @Override
    public void insert(Product product){
        if(this.poolProducts == null){
            this.poolProducts = new ArrayList<>();
        }
        poolProducts.add(product);
    }

    @Override
    public void remove(Product product){
        if(this.poolProducts == null){
            this.poolProducts = new ArrayList<>();
        }
        poolProducts.remove(product);
    }

    @Override
    public Iterable<Product> getAll(){
        return Collections.unmodifiableList(poolProducts);
    }

    @Override
    public List<Product> getAscSortedList(){
        return Collections.unmodifiableList(
                poolProducts.stream()
                        .sorted(Comparator.comparing(Product::getName))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<Product> getDescSortedList(){
        return Collections.unmodifiableList(
                poolProducts.stream()
                        .sorted(Comparator.comparing(Product::getName).reversed())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<Product> findByName(String name){
        return poolProducts.stream()
                .filter(product -> product.getName().contains(name))
                .findFirst();
//                .map(); -> it should have a transformation to an another object that is a immutable object
    }
}
