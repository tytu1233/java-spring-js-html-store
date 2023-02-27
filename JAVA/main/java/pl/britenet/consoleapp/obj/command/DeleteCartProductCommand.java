package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteCartProductCommand extends Command {

    private final CartProductService cartProductService;

    public DeleteCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_DELETE_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wprowadź ID rekordu, który chcesz usunąć");

        int id = scanner.nextInt();

        if(this.cartProductService.getCartProduct(id).isEmpty()) {
            System.out.println("Nie ma rekordu o takim ID");
            return;
        }

        CartProduct cartProduct = new CartProductBuilder(id)
                .getCartProduct();



        this.cartProductService.deleteCartProduct(cartProduct);
        System.out.println("Pomyślnie usnięto rekord o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
