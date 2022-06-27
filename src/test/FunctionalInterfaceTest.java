package test;

import main.domain.model.Product;
import main.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import main.service.ProductValidationService;
import main.util.Util;
import main.util.mock.MockProducts;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfaceTest {

    @Test
    public void testFunctionThatReturnSoftwareProducts(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        List<Product> softwareProducts = Util.filterSoftwareProducts.apply(products);
    }

    @Test
    public void testFunctionThatReturnHardwareProducts(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        List<Product> softwareProducts = Util.filterHardwareProducts.apply(products);
    }

    @Test
    public void testFunctionThatParsesProductsCreationDate(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        products.forEach(product -> System.out.println(Util.parseDate.apply(product.getCreationDate())));
    }

    @Test
    public void testMapToDescList(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
//        products.stream()
//                .map(product -> product.getName())
//                .forEach(System.out::println);

//        products.stream()
//                .mapToDouble(product -> product.getPrice())
//                .forEach(System.out::println);

//        Stream.of("one", "two", "three", "four")
//                .filter(e -> e.length() > 3)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .map(String::toUpperCase)
//                .peek(e -> System.out.println("Mapped value: " + e))
//                .collect(Collectors.toList());

        System.out.println(Stream.of("one", "two", "three", "four").count());
    }

    @Test
    public void testFunctionThatVerifiesIfDatesAreInSameWeek(){
        String result = Util.isSameWeek.apply(LocalDate.now().minusDays(1), LocalDate.now().minusDays(10));
        Assert.assertTrue(result.equals("Not same week"));
    }

    @Test
    public void testCombinatorPattern(){
        //combinator pattern
        Product product = MockProducts.getDefaultProduct.get();
        Boolean result = ProductValidationService.isCreationDateValid()
                .and(ProductValidationService.isDescValid())
                .and(ProductValidationService.isPriceValid())
                .apply(product);

        System.out.println(result);
    }



}
