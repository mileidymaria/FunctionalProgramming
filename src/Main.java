import domain.enums.Category;
import domain.model.Product;
import repository.ProductRepository;
import service.ProductValidationService;
import util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        insertElementsInRepository();
        List<Product> products = ProductRepository.getSoleInstance().getAscSortedList();

        List<Product> softwareProducts = Util.filterSoftwareProducts.apply(products);

        System.out.println("\nHARDWARE ONES: \n");
        Util.filterHardwareProducts.apply(products).forEach(System.out::println);

        System.out.println("\nSOFTWARE ONES WITH STRING PARSED DATES: \n");
        softwareProducts.forEach(sftwr -> System.out.println(Util.parseDate.apply(sftwr.getCreationDate())));

        Util.print.accept(Util.isSameWeek.apply(LocalDate.now().minusDays(1), LocalDate.now().minusDays(10)));
        Util.print.accept(Util.getTodaysDateToString.get());
        Util.printAllProductsDescriptions.accept(products);

        Util.print.accept(products.stream().map(product -> product.getCreationDate()).collect(Collectors.toList()));

        boolean isAllDescLengthLessThanTen = products.stream().allMatch(product -> product.getDesc().length() < 10);
        boolean isAnyDescLengthLessThanTen = products.stream().anyMatch(product -> product.getDesc().length() < 10);
        boolean isNoneDescLengthLessThanTen = products.stream().noneMatch(product -> product.getDesc().length() < 10);

        Util.print.accept(isAllDescLengthLessThanTen);
        Util.print.accept(isAnyDescLengthLessThanTen);
        Util.print.accept(isNoneDescLengthLessThanTen);

        Product product = new Product(ProductRepository.getSoleInstance().getNextId(), "Mouse", "fddf", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 36.6);
        System.out.println(Optional.ofNullable(null).orElseGet(() -> "default value"));
        System.out.println(Optional.of(product).orElseThrow(()-> new RuntimeException()));

        System.out.println(products);
        Optional.ofNullable(product).ifPresent(prod -> ProductRepository.getSoleInstance().insert(prod));
        products = ProductRepository.getSoleInstance().getAscSortedList();
        System.out.println(products);

        Util.print.accept(Period.between(LocalDate.of(2000,2,25), LocalDate.now()).toString()); // return is P(int number of years passed)Y(int number of months passed)M

        //combinator pattern
        Boolean result = ProductValidationService
                .isCreationDateValid()
                .and(ProductValidationService.isDescValid())
                .and(ProductValidationService.isPriceValid())
                .apply(product);

        System.out.println(result);

        //callbacks
        fooProduct(null, Util.print);
        fooProduct(product, Util.print);
        fooProduct(null, () ->{
            System.out.println("null product with runnable");
        });
    }

    static void fooProduct(Product product, Consumer<Object> callback){
        if(product != null){
            callback.accept(product);
            return;
        }
        callback.accept("Null product");
    }

    static void fooProduct(Product product, Runnable callback){
        if(product != null){
            return;
        }
        callback.run();
    }

    private static void insertElementsInRepository(){
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Mouse", "Mouse Gamer VTX", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 36.6));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Teclado", "Teclado Gamer VTX", 3, Category.HARDWARE, LocalDate.now().minusDays(3), 55.70));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Monitor", "Monitor OLED", 47, Category.HARDWARE, LocalDate.now().minusDays(7), 900.60));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Notebook", "LENOVO Ideapad", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 5600.80));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Windows", "Windows 10", 1000, Category.SOFTWARE, LocalDate.now().minusDays(14), 300));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Pacote Office", "2019", 1000, Category.SOFTWARE, LocalDate.now().minusDays(21), 180));
    }

}
