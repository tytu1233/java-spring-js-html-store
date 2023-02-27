package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.UserCart;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.UserCartService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAvgUserCartCommand extends Command {

    private final UserCartService userCartService;

    public GetAvgUserCartCommand(UserCartService userCartService) {
        super(Constants.COMMAND_GET_AVG_USER_CART);
        this.userCartService = userCartService;
    }

    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<UserCart> paginate = new Pagination<>(this.userCartService.getAverageCarts().get());
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
            paginate.getRecords(command).forEach(userCart -> System.out.println("ID użytkownika: " + userCart.getUser().getId() + ", imie: " + userCart.getUser().getName() + ", nazwisko: " + userCart.getUser().getSurname() + ", id koszyka: " + userCart.getCart().getId() + ", cena: " + userCart.getCart().getFinalPrice()));
            System.out.println("Operacja: ");
        }
    }
}
