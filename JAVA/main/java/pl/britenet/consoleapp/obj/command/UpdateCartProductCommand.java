package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCartProductCommand extends Command {

    private CartProductService cartProductService;

    public UpdateCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_UPDATE_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wporwadź ID rekordu, który chcesz zakutalizować");
        int id = scanner.nextInt();

        if(this.cartProductService.getCartProduct(id).isEmpty()) {
            System.out.println("Rekord o takim ID nie istnieje");
            return;
        }

        System.out.println("Wprowadź ID koszyka: ");
        int cartId = scanner.nextInt();
        System.out.println("Wprowadź ID produktu: ");
        int productId = scanner.nextInt();
        System.out.println("Wprowadź ilość: ");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder(id)
                .setCartId(cartId)
                .setProductId(productId)
                .setQuantity(quantity)
                .getCartProduct();


            this.cartProductService.updateCartProduct(cartProduct);
            System.out.println("Pomyślnie zaktualizowano dane!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - ilość oraz id muszą być liczbami!");
        }

    }
}
