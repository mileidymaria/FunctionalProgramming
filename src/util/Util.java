package util;

import domain.enums.Category;
import domain.model.Product;
import domain.model.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Util {

    //predicates
    public static Predicate<Product> hardwareProduct = product -> product.getCategory().equals(Category.HARDWARE); //accepts a type T and return true or false
    public static Predicate<Product> softwareProduct = product -> product.getCategory().equals(Category.SOFTWARE); //accepts a type T and return true or false
    public static BiPredicate<Product, Double> isProductPriceAbove = (product, price) -> product.getPrice() > price;
    public static BiPredicate<Product, Double> isProductPriceBelow = (product, price) -> product.getPrice() < price;
    public static BiPredicate<LocalDate, LocalDate> areDatesInBothWeek = (dayOne, dayTwo) -> LocalDate.now().compareTo(dayOne) < 7 && LocalDate.now().compareTo(dayTwo) < 7;

    /*
    * the args have to be the wrapper class of primitives, the object version, so we can attribute null values. In primitives vars we cant pass null numbers
    * */

    public static BiFunction<Product, Range<Double>, Boolean> checkPriceRange =
            (product, range) -> isProductPriceAbove.test(product, range.getStart()) && isProductPriceBelow.test(product, range.getStart());
//           this first is the first arg, this second is for the second argument, the part after arrow is about the return

    public static Function<List<Product>, List<Product>> filterHardwareProducts = input -> input
            .stream()
            .filter(p -> hardwareProduct.test(p))
            .collect(Collectors.toList());

    public static Function<List<Product>, List<Product>> filterSoftwareProducts = input -> input
            .stream()
            .filter(p -> softwareProduct.test(p))
            .collect(Collectors.toList());

    //one arg, one result
    public static Function<LocalDate, String> parseDate = input -> input.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    //two arg, one result
    public static BiFunction<LocalDate, LocalDate, String> isSameWeek =
            (dayOne, dayTwo) -> areDatesInBothWeek.test(dayOne, dayTwo) ? "Same week" : "Not same week";

    public static Consumer<List<Product>> printAllProductsDescriptions = products -> products.forEach(product -> System.out.println(" " + product.getDesc() + " "));

    public static Consumer<Object> print = item -> System.out.println("\n" + item + "\n");
    //                                     the "item" is related to the data input, second part after the arrow is the logical part to return something. if there's more than one line, there's a need to use braces {}

    public static Supplier<String> getTodaysDateToString = () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

}
