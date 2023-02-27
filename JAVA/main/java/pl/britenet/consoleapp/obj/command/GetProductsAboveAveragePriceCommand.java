package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.HigherQuantity;
import pl.britenet.consoleapp.service.HigherQuantityService;
import pl.britenet.consoleapp.service.Pagination;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetProductsAboveAveragePriceCommand extends Command {

    private final HigherQuantityService higherQuantityService;

    public GetProductsAboveAveragePriceCommand(HigherQuantityService higherQuantityService) {
        super(Constants.COMMAND_GET_PRODUCT_ABOVE_AVERAGE);
        this.higherQuantityService = higherQuantityService;
    }


    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<HigherQuantity> paginate = new Pagination<>(this.higherQuantityService.getProdcutsAboveAveragePrice().get());
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
            paginate.getRecords(command).forEach(productsAboveAverage -> {
                System.out.println("ID produktu: " + productsAboveAverage.getProduct().getId() + ", nazwa produktu: " + productsAboveAverage.getProduct().getName() + ", cena: " + productsAboveAverage.getProduct().getPrice() + ", ilość sprzedanych: " + productsAboveAverage.getOrderProduct().getQuantity());
            });
            System.out.println("Operacja: ");
        }
    }
}
