package test;

import domain.model.Product;
import org.junit.Test;
import repository.ProductRepository;
import util.Util;
import util.mock.MockProducts;

import java.util.List;
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
}
