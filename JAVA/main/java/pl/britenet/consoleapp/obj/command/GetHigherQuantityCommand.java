package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.HigherQuantity;
import pl.britenet.consoleapp.service.HigherQuantityService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetHigherQuantityCommand extends Command {

    private final HigherQuantityService higherQuantityService;

    public GetHigherQuantityCommand(HigherQuantityService higherQuantityService) {
        super(Constants.COMMAND_GET_QUANTITY_HIGHER);
        this.higherQuantityService = higherQuantityService;
    }


    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<HigherQuantity> paginate = new Pagination<>(this.higherQuantityService.getAllHigherQuantity().get());
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
            paginate.getRecords(command).forEach(higherQuantity -> System.out.println("ID produktu: " + higherQuantity.getProduct().getId() + ", id_order_product: " + higherQuantity.getOrderProduct().getId() + ", nazwa produktu: " + higherQuantity.getProduct().getName() + ", ilość: " + higherQuantity.getOrderProduct().getQuantity()));
            System.out.println("Operacja: ");
        }
    }
}
