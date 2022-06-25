package service;

import domain.model.Product;

import java.time.LocalDate;
import java.util.function.Function;

public interface ProductValidationService extends Function<Product, Boolean> {
    static ProductValidationService isDescValid(){
        return product -> product.getDesc().length() > 0;
    }
    static ProductValidationService isPriceValid(){
        return product -> product.getPrice() > 0;
    }
    static ProductValidationService isCreationDateValid(){
        return product -> product.getCreationDate().compareTo(LocalDate.now()) <=0;
    }

    //combinator pattern
    default ProductValidationService and (ProductValidationService other){
        return product -> {
            return this.apply(product) ? other.apply(product) : false;
        };
    }

    //combinator pattern
    default ProductValidationService or (ProductValidationService other){
        return product -> {
            return this.apply(product) ? other.apply(product) : false;
        };
    }
}
