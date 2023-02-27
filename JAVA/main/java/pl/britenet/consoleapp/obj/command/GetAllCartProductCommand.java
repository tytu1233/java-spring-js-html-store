package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.CartProductService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Optional;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllCartProductCommand extends Command {

    private final CartProductService cartProductService;

    public GetAllCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_GET_ALL_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<CartProduct> paginate = new Pagination<>(this.cartProductService.getAllProducts().get());
        System.out.println("Podaj liczbe wierszy, którą chcesz wyświetlić");
        int amount;
        while(true){
            try{
                amount = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe - liczba musi być całkowita");
            }
        }
        paginate.setAmount(amount);
        scanner.nextLine();
        System.out.println("Wpisz '?', aby wyświetlić opcje:");
        while(ON) {
            String command = scanner.nextLine();
            paginate.getRecords(command).forEach(cartProduct -> {
                System.out.println("ID: " + cartProduct.getId() + ", cart ID: " + cartProduct.getCartId() + ", product ID: " + cartProduct.getProductId() + ", ilość: " + cartProduct.getQuantity());
            });
            System.out.println("Operacja: ");
        }
    }
}
