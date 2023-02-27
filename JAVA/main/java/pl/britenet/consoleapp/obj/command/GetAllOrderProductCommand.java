package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllOrderProductCommand extends Command {

    private final OrderProductService orderProductService;

    public GetAllOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_GET_ALL_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<OrderProduct> paginate = new Pagination<>(this.orderProductService.getAllOrderProducts(1).get());
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
            paginate.getRecords(command).forEach(orderProduct -> System.out.println("ID: " + orderProduct.getId() + ", ilość: " + orderProduct.getQuantity() + ", cena: " + orderProduct.getPrice() + " zł, order ID: " + orderProduct.getOrderId() + ", product ID: " + orderProduct.getProductId()));
            System.out.println("Operacja: ");
        }
    }
}
