package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertOrderProductCommand extends Command{

    private final OrderProductService orderProductService;

    public InsertOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_INSERT_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        try {
        System.out.println("Podaj dane do wprowadzenia rekordu");

        System.out.println("Podaj ilość: ");
        int quantity = scanner.nextInt();
        System.out.println("Podaj cenę: ");
        Double price = scanner.nextDouble();
        System.out.println("Podaj order ID: ");
        int orderId = scanner.nextInt();
        System.out.println("Podaj product ID: ");
        int productId = scanner.nextInt();

        OrderProduct orderProduct = new OrderProductBuilder()
                .setQuantity(quantity)
                .setPrice(price)
                .setOrderId(orderId)
                .setProductId(productId)
                .getOrderProduct();

            this.orderProductService.insertOrderProduct(orderProduct);
            System.out.println("Pomyślnie dodano rekord!");
        } catch (IllegalStateException e) {
            System.out.println("Nie ma takieg ID!");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena oraz id muszą być liczbami!");
        }
    }
}
