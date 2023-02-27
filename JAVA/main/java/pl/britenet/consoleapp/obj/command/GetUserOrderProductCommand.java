package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.UserOrderProduct;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.UserOrderProductService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetUserOrderProductCommand extends Command {

    private final UserOrderProductService userOrderProductService;

    public GetUserOrderProductCommand(UserOrderProductService userOrderProductService) {
        super(Constants.COMMAND_GET_USERS_ORDERS);
        this.userOrderProductService = userOrderProductService;
    }

    @Override
    public void execute() {


        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<UserOrderProduct> paginate = new Pagination<>(this.userOrderProductService.getUserOrderProduct().get());
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
            paginate.getRecords(command).forEach(userOrderProduct -> {
                System.out.println("ID użytkownika: " + userOrderProduct.getUser().getId() + ", imie: " + userOrderProduct.getUser().getName() + ", nazwisko: " + userOrderProduct.getUser().getSurname() + ", login: " + userOrderProduct.getUser().getLogin() + ", nazwa produktu: " + userOrderProduct.getProduct().getName());
            });
            System.out.println("Operacja: ");
        }
    }
}
