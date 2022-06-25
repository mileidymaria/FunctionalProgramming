package repository;

import domain.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {
    private static ProductRepository soleInstance = null;
    private List<Product> poolProducts;

    public ProductRepository() {
    }

    public int getNextId(){
        if(poolProducts == null){
            return 1;
        }
        return poolProducts.size() + 1;
    }

    public static ProductRepository getSoleInstance(){
        if(soleInstance == null){
           soleInstance = new ProductRepository();
        }
        return soleInstance;
    }

    public void insert(Product product){
        if(this.poolProducts == null){
            this.poolProducts = new ArrayList<>();
        }
        poolProducts.add(product);
    }

    public void remove(Product product){
        if(this.poolProducts == null){
            this.poolProducts = new ArrayList<>();
        }
        poolProducts.remove(product);
    }

    public Iterable<Product> getAll(){
        return Collections.unmodifiableList(poolProducts);
    }

    public List<Product> getAscSortedList(){
        //sort
        return Collections.unmodifiableList(poolProducts);
    }

    public List<Product> getDescSortedList(){
        //sort
        return Collections.unmodifiableList(poolProducts);
    }

}
