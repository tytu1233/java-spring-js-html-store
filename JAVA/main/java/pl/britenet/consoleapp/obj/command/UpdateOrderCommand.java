package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderBuilder;
import pl.britenet.consoleapp.obj.model.Order;
import pl.britenet.consoleapp.service.OrderService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateOrderCommand extends Command {

    private final OrderService orderService;

    public UpdateOrderCommand(OrderService orderService) {
        super(Constants.COMMAND_UPDATE_ORDER);
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {
        System.out.println("Wprowadź ID zamówienia, które chcesz zaktualizować");
        int id = scanner.nextInt();

        if(this.orderService.getOrder(id).isEmpty()) {
            System.out.println("Zamówienie o takim ID nie istnieje!");
            return;
        }

        System.out.println("Wprowadź status zamówienia:");
        scanner.nextLine();
        String status = scanner.nextLine();
        System.out.println("Wprowadź datę (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.println("Wprowadź cenę całości zamówienia:");
        double finalPrice = scanner.nextDouble();
        System.out.println("Wprowadź id użytkownika");
        int userId = scanner.nextInt();

        Order order = new OrderBuilder(id)
                .setStatus(status)
                .setDate(date)
                .setFinalPrice(finalPrice)
                .setUserId(userId)
                .getOrder();

            this.orderService.updateOrder(order);
            System.out.println("Pomyślnie zaktualizowano zamówienie");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena oraz id muszą być liczbami!");
        }
    }
}
