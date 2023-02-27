package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertCartProductCommand extends Command {

    private final CartProductService cartProductService;

    public InsertCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_INSERT_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wprowadź dane dla tabeli łączącej");
        String sign = ""+'+';
        System.out.println("Wprowadź ID koszyka: ");
        int cartId = scanner.nextInt();
        System.out.println("Wprowadź ID produktu: ");
        int productId = scanner.nextInt();
        System.out.println("Wprowadź ilość: ");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder()
                .setCartId(cartId)
                .setProductId(productId)
                .setQuantity(quantity)
                .getCartProduct();

            this.cartProductService.insertCartProduct(cartProduct, sign);
            System.out.println("Pomyślnie wprowadzono dane!");
        } catch (IllegalStateException e) {
            System.out.println("Nie ma takieg ID!");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
