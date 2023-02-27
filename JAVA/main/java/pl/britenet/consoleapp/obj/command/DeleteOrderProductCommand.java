package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteOrderProductCommand extends Command {

    private final OrderProductService orderProductService;

    public DeleteOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_DELETE_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Podaj ID rekordu, który chcesz usunąć: ");
        int id = scanner.nextInt();

        if(this.orderProductService.getOrderProduct(id).isEmpty()) {
            System.out.println("Rekord o takim ID nie istnieje");
            return;
        }

        OrderProduct orderProduct = new OrderProductBuilder(id)
                .getOrderProduct();

        this.orderProductService.deleteOrderProduct(orderProduct);
        System.out.println("Pomyślnie usunięto rekord o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (
        InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
