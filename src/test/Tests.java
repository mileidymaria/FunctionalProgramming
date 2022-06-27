package test;

import main.domain.model.Product;
import org.junit.Assert;
import org.junit.Test;
import main.util.Util;
import main.util.mock.MockProducts;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.function.Consumer;

public class Tests {

    @Test
    @DisplayName("Display")
    @Disabled
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

    @ParameterizedTest
    @ValueSource(strings = {"notebook", "mouse", "monitor", "teclado"})
    public void testFooParameterized(String name){
        Assert.assertTrue(name.length()<50);
    }

//    @ParameterizedTest
//    @MethodSource("methodSourceListOfString")
//    public void testFooParameterizedWithMethodSource(String name){
//        Assert.assertTrue(name.length()<50);
//    }
//
//    private static List<String> methodSourceListOfString(){
//        return Arrays.asList("notebook", "mouse", "monitor", "teclado");
//    }


}
