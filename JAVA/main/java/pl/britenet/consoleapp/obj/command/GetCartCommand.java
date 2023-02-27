package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.Optional;
import java.util.Scanner;

public class GetCartCommand extends Command {

    private final CartService cartService;

    public GetCartCommand(CartService cartService) {
        super(Constants.COMMAND_GET_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID koszyka");

        int id = scanner.nextInt();

        Optional<Cart> optionalCart = this.cartService.getCart(id);

        if(optionalCart.isPresent()) {

            System.out.println("Dane koszyka: ");
            System.out.println("ID: " + optionalCart.get().getId());
            System.out.println("ID użytkownika: " + optionalCart.get().getUserId());
            System.out.println("Cena końcowa: " + optionalCart.get().getFinalPrice() + " zł");

        } else {
            System.out.println("Koszyk o podanym ID nie istnieje!");
        }

    }
}
