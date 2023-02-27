package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.ReturnedOrders;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.ReturnedOrdersService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetReturnedOrdersCommand extends Command {

    private final ReturnedOrdersService returnedOrdersService;

    public GetReturnedOrdersCommand(ReturnedOrdersService returnedOrdersService) {
        super(Constants.COMMAND_GET_RETURNEDORDERS);
        this.returnedOrdersService = returnedOrdersService;
    }


    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<ReturnedOrders> paginate = new Pagination<>(this.returnedOrdersService.getReturnedOrders().get());
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
            paginate.getRecords(command).forEach(returnedOrders -> {
                System.out.println("ID produktu: " + returnedOrders.getProduct().getId() + ", id zamówienia: " + returnedOrders.getOrder().getId() + ", nazwa produktu: " + returnedOrders.getProduct().getName() + ", cena produktu: " + returnedOrders.getProduct().getPrice() + ", ilość: " + returnedOrders.getOrderProduct().getQuantity());
            });
            System.out.println("Operacja: ");
        }
    }
}
