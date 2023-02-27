package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetProductCommand extends Command {

    private final ProductService productService;

    public GetProductCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu, które chcesz wyświetlić:");
        int id = scanner.nextInt();

        Optional<Product> optionalProduct = this.productService.getProduct(id);


        if (optionalProduct.isPresent()) {
            System.out.println("Szczegóły produktu: \n");
            System.out.println("ID: " + optionalProduct.get().getId());
            System.out.println("Nazwa: " + optionalProduct.get().getName());
            System.out.println("Opis: " + optionalProduct.get().getDescription());
            System.out.println("Cena: " + optionalProduct.get().getPrice() + " zł");
            System.out.println("Image: " + optionalProduct.get().getImage() + " zł");
        }
        else {
            System.out.println("Produkt o takim ID nie istnieje");
        }
    }
}
