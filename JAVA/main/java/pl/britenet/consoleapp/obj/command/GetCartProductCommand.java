package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetCartProductCommand extends Command {

    private final CartProductService cartProductService;

    public GetCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_GET_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID, które chcesz zobaczyć: ");
        int id = scanner.nextInt();

        Optional<CartProduct> optionalCartProduct = this.cartProductService.getCartProduct(id);

        if(optionalCartProduct.isPresent()) {
            System.out.println("ID: " + optionalCartProduct.get().getId());
            System.out.println("Cart ID: " + optionalCartProduct.get().getCartId());
            System.out.println("Product ID: " + optionalCartProduct.get().getProductId());
            System.out.println("Ilość: " + optionalCartProduct.get().getQuantity());
        } else {
            System.out.println("Rekord o takim ID nie istnieje!");
        }

    }
}
