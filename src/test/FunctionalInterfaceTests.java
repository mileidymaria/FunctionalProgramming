package test;

import domain.model.Product;
import org.junit.Assert;
import org.junit.Test;
import repository.ProductRepository;
import service.ProductValidationService;
import util.Util;
import util.mock.MockProducts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FunctionalInterfaceTests {

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
    public void testFunctionThatVerifiesIfDatesAreInSameWeek(){
        String result = Util.isSameWeek.apply(LocalDate.now().minusDays(1), LocalDate.now().minusDays(10));
        Assert.assertTrue(result.equals("Not same week"));
    }

    @Test
    public void testCombinatorPattern(){
        //combinator pattern
        Product product = MockProducts.getDefaultProduct.get();
        Boolean result = ProductValidationService
                .isCreationDateValid()
                .and(ProductValidationService.isDescValid())
                .and(ProductValidationService.isPriceValid())
                .apply(product);

        System.out.println(result);
    }



}
