package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.HigherQuantity;
import pl.britenet.consoleapp.service.HigherQuantityService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetComplitingOrdersCommand extends Command {

    private final HigherQuantityService higherQuantityService;

    public GetComplitingOrdersCommand(HigherQuantityService higherQuantityService) {
        super(Constants.COMMAND_GET_COMPLITING_ORDERS);
        this.higherQuantityService = higherQuantityService;
    }



    @Override
    public void execute() {


        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<HigherQuantity> paginate = new Pagination<>(this.higherQuantityService.getComplitingProducts().get());
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
            paginate.getRecords(command).forEach(getCompiting -> System.out.println("Id produktu: " + getCompiting.getProduct().getId() + ", nazwa: " + getCompiting.getProduct().getName() + ", ilość: " + getCompiting.getOrderProduct().getQuantity()));
            System.out.println("Operacja: ");
        }
    }
}
