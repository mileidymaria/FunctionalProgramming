package test;

import main.domain.enums.Category;
import main.domain.model.Product;
import main.repository.ProductRepository;

import main.util.Util;
import main.util.mock.MockProducts;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StreamTest {

    private List<Product> products;

    @BeforeEach
    public void setupAll(){
        MockProducts.insertElementsInRepository();
        products = ProductRepository.getSoleInstance().getAscSortedList();
    }

    @Test
    public void testStreamMatches(){

        boolean isAllDescLengthLessThanTen = products.stream().allMatch(product -> product.getDesc().length() < 10);
        boolean isAnyDescLengthLessThanTen = products.stream().anyMatch(product -> product.getDesc().length() < 10);
        boolean isNoneDescLengthLessThanTen = products.stream().noneMatch(product -> product.getDesc().length() < 10);

        Util.print.accept(isAllDescLengthLessThanTen);
        Util.print.accept(isAnyDescLengthLessThanTen);
        Util.print.accept(isNoneDescLengthLessThanTen);
    }

    @Test
    public void testFilterByPriceWithLimit(){
        products.stream()
                .filter(product -> product.getPrice() < 10_000.00d)
                .filter(product -> product.getDesc().length() < 10)
                .limit(4)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void transformProductListInFooList() {
        products.stream()
                .map(product -> product.getDesc())
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    @Test
    public void sortProductList() {
        products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream()
                .sorted(Comparator.comparing(Product::getName).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream()
                .sorted(Comparator.comparing(Product::getName).thenComparing(Product::getPrice))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    @RepeatedTest(value = 5, name = "Repeating maxMin {currentRepetition} of 5")
    public void maxMin(){
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .ifPresent(System.out::println);
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream().min(Comparator.comparing(Product::getPrice)).ifPresent(System.out::println);
    }

    @Test
    public void group(){
        Map<Category, List<Product>> groupByCat = products.stream().collect(Collectors.groupingBy(Product::getCategory));
        groupByCat.forEach((cat, prods) -> {
            System.out.println(cat);
            prods.forEach(System.out::println);
        });
    }

    @Test
//    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Just testing things")
    public void filterMaxAnderReturnName(){
        Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
        products.stream()
                .filter(product -> product.getCategory().equals(Category.HARDWARE))
                .max(Comparator.comparing(Product::getPrice))
                .map(product -> product.getName())
                .ifPresent(System.out::println);
    }

    @AfterEach
    public void tearDown(){
        products = new ArrayList<>();
    }
}
