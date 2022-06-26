package test;

import domain.model.Product;
import org.junit.Test;
import util.Util;
import util.mock.MockProducts;

import java.util.function.Consumer;

public class Tests {

    @Test
    public void testCallbacks(){
        Product product = MockProducts.getDefaultProduct.get();
        //consumer
        fooProduct(null, Util.print);
        //consumer
        fooProduct(product, Util.print);
        //runnable
        fooProduct(null, () ->{
            System.out.println("null product with runnable");
        });
    }

    void fooProduct(Product product, Consumer<Object> callback){
        if(product != null){
            callback.accept(product);
            return;
        }
        callback.accept("Null product");
    }

    void fooProduct(Product product, Runnable callback){
        if(product != null){
            return;
        }
        callback.run();
    }
}
