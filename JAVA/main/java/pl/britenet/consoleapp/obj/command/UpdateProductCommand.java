package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateProductCommand extends Command {


    private final ProductService productService;

    public UpdateProductCommand(ProductService productService) {
        super(Constants.COMMAND_UPDATE_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        try {
        System.out.println("Wprowadź ID produktu:");
        int id = scanner.nextInt();

        if(this.productService.getProduct(id).isEmpty()) {
            System.out.println("Produkt o takim ID nie istnieje");
            return;
        }



        System.out.println("Wprowadź nazwę produktu:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Wprowadź opis produktu:");
        String description = scanner.nextLine();
        System.out.println("Wprowadź cenę produktu:");
        double price = scanner.nextDouble();

        Product product = new ProductBuilder(id)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .getProduct();

            this.productService.updateProduct(product);
            System.out.println("Zaktualizowano produkt");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena musi być liczbą!");
        }
    }
}
