package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.UserOrders;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.UserOrdersService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetFirstOrderCommand extends Command {

    private final UserOrdersService userOrdersService;

    public GetFirstOrderCommand(UserOrdersService userOrdersService) {
        super(Constants.COMMAND_GET_FIRST_ORDER);
        this.userOrdersService = userOrdersService;
    }


    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<UserOrders> paginate = new Pagination<>(this.userOrdersService.getFirstOrder().get());
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
            paginate.getRecords(command).forEach(userOrders -> System.out.println("ID zamówienia: " + userOrders.getOrder().getId() + ", id użytkownika: " + userOrders.getUser().getId() + ", imie: " + userOrders.getUser().getName() + ", nazwisko: " + userOrders.getUser().getSurname() + ", data: " + userOrders.getOrder().getDate()));
            System.out.println("Operacja: ");
        }
    }
}
