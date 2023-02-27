package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllCartCommand extends Command {

    private final CartService cartService;

    public GetAllCartCommand(CartService cartService) {
        super(Constants.COMMAND_GET_ALL_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {


        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<Cart> paginate = new Pagination<>(this.cartService.getAllCarts().get());
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
            paginate.getRecords(command).forEach(cart -> {
                System.out.println("ID: " + cart.getId() + ", user ID: " + cart.getUserId() + ", cena końcowa: " + cart.getFinalPrice() + " zł");
            });
            System.out.println("Operacja: ");
        }
    }
}
