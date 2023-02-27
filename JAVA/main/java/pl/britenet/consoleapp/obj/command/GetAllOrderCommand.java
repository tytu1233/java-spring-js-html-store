package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.OrderService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllOrderCommand extends Command {

    private final OrderService orderService;

    public GetAllOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_GET_ALL_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<Order> paginate = new Pagination<>(this.orderService.getAllOrders().get());
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
            paginate.getRecords(command).forEach(order -> System.out.println("ID: " + order.getId() + ", status: " + order.getStatus() + ", data: " + order.getDate() + ", cena końcowa: " +order.getFinalPrice() + ", adres: "+ order.getAddress() +", user ID: " + order.getUserId()));

        }
    }
}
