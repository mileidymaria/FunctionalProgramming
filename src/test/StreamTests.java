package test;

import domain.enums.Category;
import domain.model.Product;
import org.junit.Test;
import repository.ProductRepository;
import util.Util;
import util.mock.MockProducts;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTests {

    @Test
    public void testStreamMatches(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();

        boolean isAllDescLengthLessThanTen = products.stream().allMatch(product -> product.getDesc().length() < 10);
        boolean isAnyDescLengthLessThanTen = products.stream().anyMatch(product -> product.getDesc().length() < 10);
        boolean isNoneDescLengthLessThanTen = products.stream().noneMatch(product -> product.getDesc().length() < 10);

        Util.print.accept(isAllDescLengthLessThanTen);
        Util.print.accept(isAnyDescLengthLessThanTen);
        Util.print.accept(isNoneDescLengthLessThanTen);
    }

    @Test
    public void testFilterByPriceWithLimit(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        products.stream()
                .filter(product -> product.getPrice() < 10_000.00)
                .filter(product -> product.getDesc().length() < 10)
                .limit(4)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void transformProductListInFooList() {
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        products.stream()
                .map(product -> product.getDesc())
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    @Test
    public void sortProductList() {
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
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

    @Test
    public void maxMin(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream().max(Comparator.comparing(Product::getPrice)).ifPresent(System.out::println);
        System.out.println("\n--------------------------------------------------------------------\n");
        products.stream().min(Comparator.comparing(Product::getPrice)).ifPresent(System.out::println);
    }

    @Test
    public void group(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        Map<Category, List<Product>> groupByCat = products.stream().collect(Collectors.groupingBy(Product::getCategory));
        groupByCat.forEach((cat, prods) -> {
            System.out.println(cat);
            prods.forEach(System.out::println);
        });
    }

    @Test
    public void filterMaxAnderReturnName(){
        MockProducts.insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();
        products.stream()
                .filter(product -> product.getCategory().equals(Category.HARDWARE))
                .max(Comparator.comparing(Product::getPrice))
                .map(product -> product.getName())
                .ifPresent(System.out::println);
    }
}
