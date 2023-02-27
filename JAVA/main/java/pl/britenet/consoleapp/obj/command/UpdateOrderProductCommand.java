package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateOrderProductCommand extends Command {

    private final OrderProductService orderProductService;

    public UpdateOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_UPDATE_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try {

        System.out.println("Podaj ID rekordu, który chcesz zaktualizować: ");
        int id = scanner.nextInt();

        if(this.orderProductService.getOrderProduct(id).isEmpty()) {
            System.out.println("Rekord o takim ID nie istnieje");
            return;
        }

        System.out.println("Podaj ilość: ");
        int quantity = scanner.nextInt();
        System.out.println("Podaj cenę: ");
        Double price = scanner.nextDouble();
        System.out.println("Podaj order ID: ");
        int orderId = scanner.nextInt();
        System.out.println("Podaj product ID: ");
        int productId = scanner.nextInt();

        OrderProduct orderProduct = new OrderProductBuilder(id)
                .setQuantity(quantity)
                .setPrice(price)
                .setOrderId(orderId)
                .setProductId(productId)
                .getOrderProduct();


            this.orderProductService.updateOrderProduct(orderProduct);
            System.out.println("Pomyślnie zaktualizowano rekord!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena, ilość, oraz id muszą być liczbami!");
        }
    }
}
