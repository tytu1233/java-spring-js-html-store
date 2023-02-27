package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteCartCommand extends Command {

    private final CartService cartService;

    public DeleteCartCommand(CartService cartService) {
        super(Constants.COMMAND_DELETE_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wprowadź ID do usunięcia: ");
        int id = scanner.nextInt();



        if(this.cartService.getCart(id).isEmpty()) {
            System.out.println("Rekord o takim ID nie istnieje");
            return;
        }

        Cart cart = new CartBuilder(id)
                .getCart();

            this.cartService.deleteCart(cart);
            System.out.println("Pomyślnie usunięto koszyk o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
