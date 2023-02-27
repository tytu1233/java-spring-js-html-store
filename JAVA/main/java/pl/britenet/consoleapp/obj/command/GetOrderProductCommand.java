package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetOrderProductCommand extends Command {

    private final OrderProductService orderProductService;

    public GetOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_GET_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ID rekordu, który chcesz wyszukać: ");

        int id = scanner.nextInt();

        Optional<OrderProduct> optionalOrderProduct = this.orderProductService.getOrderProduct(id);

        if(optionalOrderProduct.isPresent()) {
            System.out.println("ID: " + optionalOrderProduct.get().getId());
            System.out.println("Ilość: " + optionalOrderProduct.get().getQuantity());
            System.out.println("Cena: " + optionalOrderProduct.get().getPrice() + " zł");
            System.out.println("Order ID: " + optionalOrderProduct.get().getOrderId());
            System.out.println("Product ID: " + optionalOrderProduct.get().getProductId());
        } else {
            System.out.println("Brak rekordu o takim ID");
        }

    }
}
