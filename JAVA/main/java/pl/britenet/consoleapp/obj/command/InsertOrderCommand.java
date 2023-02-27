package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderBuilder;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.OrderService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;


public class InsertOrderCommand extends Command {

    private final OrderService orderService;

    public InsertOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_INSERT_ORDER);
        this.orderService = orderService;
    }
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Wprowadź status zamówienia:");
        String status = scanner.nextLine();
        System.out.println("Wprowadź datę (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.println("Wprowadź cenę całości zamówienia:");
        double finalPrice = scanner.nextDouble();
        System.out.println("Wprowadź id użytkownika");
        int userId = scanner.nextInt();

        Order order = new OrderBuilder()
                .setStatus(status)
                .setDate(date)
                .setFinalPrice(finalPrice)
                .setUserId(userId)
                .getOrder();
        try {
            this.orderService.insertOrder(order);
            System.out.println("Zamówienie zostało utworzone!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
