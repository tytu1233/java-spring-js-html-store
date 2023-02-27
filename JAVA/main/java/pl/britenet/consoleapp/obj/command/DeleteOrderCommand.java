package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderBuilder;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.OrderService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteOrderCommand extends Command {

    private final OrderService orderService;

    public DeleteOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_DELETE_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        try {
        System.out.println("Wprowadź ID zamówienia do usunięcia");
        int id = scanner.nextInt();

        if(this.orderService.getOrder(id).isEmpty()) {
            System.out.println("Rekord o takim ID nie istnieje");
            return;
        }

        Order order = new OrderBuilder(id)
                .getOrder();

            this.orderService.deleteOrder(order);
            System.out.println("Usunięto zamówienie o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
