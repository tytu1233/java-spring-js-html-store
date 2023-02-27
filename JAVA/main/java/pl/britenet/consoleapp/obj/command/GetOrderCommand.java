package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.OrderService;

import java.util.Optional;
import java.util.Scanner;

public class GetOrderCommand extends Command{

    private final OrderService orderService;

    public GetOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_GET_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID zamówienia, które chcesz wyświetlić:");
        int id = scanner.nextInt();

        Optional<Order> optionalOrder = this.orderService.getOrder(id);


        if (optionalOrder.isPresent()) {
            System.out.println("Szczegóły zamówienia: \n");
            System.out.println("ID: " + optionalOrder.get().getId());
            System.out.println("Status: " + optionalOrder.get().getStatus());
            System.out.println("Data: " + optionalOrder.get().getDate());
            System.out.println("Cena końcowa: " + optionalOrder.get().getFinalPrice() + " zł");
            System.out.println("Adres: " + optionalOrder.get().getAddress());
            System.out.println("ID użytkownika: " + optionalOrder.get().getUserId());
        }
        else {
            System.out.println("Zamówienie o takim ID nie istnieje");
        }

    }
}
