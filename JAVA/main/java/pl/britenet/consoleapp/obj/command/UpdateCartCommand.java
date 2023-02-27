package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateCartCommand extends Command {

    private final CartService cartService;

    public UpdateCartCommand(CartService cartService) {
        super(Constants.COMMAND_UPDATE_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        try {
        System.out.println("Wprowadź ID koszyka, który chcesz zakutalizować: ");
        int id = scanner.nextInt();

        if(this.cartService.getCart(id).isEmpty()) {
            System.out.println("Koszyk o takim ID nie istnieje");
            return;
        }

        System.out.println("Wprowadź ID użytkownika: ");
        int userId = scanner.nextInt();
        System.out.println("Wprowadź cenę końcową");
        Double finalPrice = scanner.nextDouble();

        Cart cart = new CartBuilder(id)
                .setUserId(userId)
                .setFinalPrice(finalPrice)
                .getCart();

            this.cartService.updateCart(cart);
            System.out.println("Pomyślnie zakutlizowano koszyk!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena oraz id muszą być liczbami!");
        }

    }
}
