package test;

import domain.enums.Category;
import domain.model.Product;
import org.junit.Test;
import repository.ProductRepository;
import util.mock.MockProducts;

import java.util.List;
import java.util.Optional;

public class OptionalTests {

    /*
     * isPresent()
     * isEmpty()
     * .of() -> sure that there's an value
     * .ofNullable() -> idk if there's an value
     * .empty() -> no value never
     * .orElse() -> receives an default value of the same type of the optional
     * .map() -> transform value inside the optional in case it's present
     * .orElseGet -> receives a supplier to get a default value
     * .ifPresent()
     * .ifPresentOrElse()
     */
    
    @Test
    public void testOptionals(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        Product product = MockProducts.getDefaultProduct.get();

//        System.out.println(Optional.ofNullable(null).orElseGet(() -> "default value"));
//        System.out.println(Optional.of(product).orElseThrow(()-> new RuntimeException()));

        Optional.ofNullable(product).ifPresent(prod -> ProductRepository.getSoleInstance().insert(prod));

        Optional.ofNullable(product).ifPresentOrElse(
                System.out::println, //present
                () -> { System.out.println("Empty"); } //or else
        );

        Optional.ofNullable(null).ifPresentOrElse(
                System.out::println, //present
                () -> { System.out.println("Empty"); } //or else
        );

    }
}
