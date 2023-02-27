package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.SoldProduct;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.SoldProductService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetSoldProductsCommand extends Command {

    private final SoldProductService soldProductService;

    public GetSoldProductsCommand(SoldProductService soldProductService) {
        super(Constants.COMMAND_GET_SOLDPRODUCTS);
        this.soldProductService = soldProductService;
    }


    @Override
    public void execute() {

        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<SoldProduct> paginate = new Pagination<>(this.soldProductService.getSoldProducts().get());
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
            paginate.getRecords(command).forEach(soldProduct -> {
                System.out.println("ID produktu: " + soldProduct.getProduct().getId() + ", nazwa produktu: " + soldProduct.getProduct().getName() + ", liczba sprzedanych: " + soldProduct.getAmount());
            });
            System.out.println("Operacja: ");
        }
    }
}
