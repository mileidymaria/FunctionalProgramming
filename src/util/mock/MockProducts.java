package util.mock;

import domain.enums.Category;
import domain.model.Product;
import repository.ProductRepository;

import java.time.LocalDate;
import java.util.function.Supplier;

public class MockProducts {

    public static void insertElementsInRepository(){
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Mouse", "Mouse Gamer VTX", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 36.6));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Teclado", "Teclado Gamer VTX", 3, Category.HARDWARE, LocalDate.now().minusDays(3), 55.70));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Monitor", "Monitor OLED", 47, Category.HARDWARE, LocalDate.now().minusDays(7), 900.60));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Notebook", "LENOVO Ideapad", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 5600.80));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Windows", "Windows 10", 1000, Category.SOFTWARE, LocalDate.now().minusDays(14), 300));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Pacote Office", "2019", 1000, Category.SOFTWARE, LocalDate.now().minusDays(21), 180));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Carregador", "Carregador VTX", 2, Category.HARDWARE, LocalDate.now().minusDays(1), 36.6));
        ProductRepository.getSoleInstance().insert(new Product(ProductRepository.getSoleInstance().getNextId(), "Mousepad", "Mousepad Gamer VTX", 3, Category.HARDWARE, LocalDate.now().minusDays(3), 55.70));
    }

    public static Supplier<Product> getDefaultProduct = () ->  new Product(0, "Mousepad", "Mousepad Gamer VTX", 3, Category.HARDWARE, LocalDate.now().minusDays(3), 55.70);



}
