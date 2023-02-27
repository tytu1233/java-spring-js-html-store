package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertProductCommand extends Command {

    private final ProductService productService;

    public InsertProductCommand(ProductService productService) {
        super(Constants.COMMAND_INSERT_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {
        System.out.println("Wprowadź nazwę produktu:");
        String name = scanner.nextLine();
        System.out.println("Wprowadź opis produktu:");
        String description = scanner.nextLine();
        System.out.println("Wprowadź cenę produktu:");
        Double price = scanner.nextDouble();
        System.out.println("Wprowadź url bądź ścieżkę do zdjęcia: ");
        scanner.nextLine();
        String image = scanner.nextLine();

        Product product = new ProductBuilder()
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setImage(image)
                .getProduct();

            this.productService.insertProduct(product);
            System.out.println("Produkt został utworzony!");
        } catch (IllegalStateException e) {
            System.out.println("Nie ma takieg ID!");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena powinna być liczbą!");
        }
    }
}
