package test;

import main.domain.model.Product;
import main.repository.ProductRepository;
import main.util.mock.MockProducts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import java.util.Optional;

//@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @BeforeAll
    public static void setupAll(){
        System.out.println("tests");
        //set mocks here, with the @ExtendWith, we don't need to use AutoCloseable to open/close our mocks
    }

    @Test
    public void findByNameTestShouldNotFound(){
        //given
        MockProducts.insertElementsInRepository();
        //when
        Optional<Product> foo = ProductRepository.getSoleInstance().findByName("Foo");
        //then
        Assert.assertFalse(foo.isPresent());
    }

    @Test
//    @Disabled //will be ignore
    public void findByNameTestShouldFound(){
        //given
        MockProducts.insertElementsInRepository();
        //when
        Optional<Product> foo = ProductRepository.getSoleInstance().findByName("Mouse");
        //then
        Assert.assertTrue(foo.get().toString(),foo.isPresent());
    }

    @BeforeEach
    public void setUp(){
        System.out.println("SOUT");
        //set mocks here, with the @ExtendWith, we don't need to use AutoCloseable to open/close our mocks
    }

    @Test
    public void itShouldIncrementPrice(){
        Product product = MockProducts.getDefaultProduct.get();
        var percentDiscount = 0.70;
        var expected = product.getPrice() * percentDiscount;
        Assert.assertTrue("The primites are different",expected == product.applyPromo(percentDiscount));
    }


}
