package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteProductCommand extends Command{

    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        super(Constants.COMMAND_DELETE_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wprowadź ID produktu, które chcesz usunąć:");
        int id = scanner.nextInt();

        if(this.productService.getProduct(id).isEmpty()) {
            System.out.println("Nie ma rekordu o takim ID");
            return;
        }

        Product product = new ProductBuilder(id)
                .getProduct();

            this.productService.deleteProduct(product);
            System.out.println("Usunięto produkt o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }

}
