package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertCartCommand extends Command {

    private final CartService cartService;

    public InsertCartCommand(CartService cartService) {
        super(Constants.COMMAND_INSERT_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        try {
        System.out.println("Wprowadź dane koszyka: ");

        System.out.println("Podaj id użytkownika: ");
        int userId = scanner.nextInt();
        System.out.println("Podaj końcową cenę: ");
        Double finalPrice = scanner.nextDouble();

        Cart cart = new CartBuilder()
                .setUserId(userId)
                .setFinalPrice(finalPrice)
                .getCart();

            this.cartService.insertCart(cart);
            System.out.println("Wprowadzono koszyk!");
        } catch (IllegalStateException e) {
            System.out.println("Nie istnieje użytkownik o takim ID!");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
