package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllProductCommand extends Command {

    private final ProductService productService;

    public GetAllProductCommand(ProductService productService) {
        super(Constants.COMMAND_GET_ALL_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<Product> paginate = new Pagination<>(this.productService.getAllProducts().get());
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
            String command  = scanner.nextLine();
            paginate.getRecords(command).forEach(product -> System.out.println("ID: " + product.getId() + ", nazwa: " + product.getName() + ", opis: " + product.getDescription() + ", cena: " + product.getPrice() + " zł" + ", zdjecie: " + product.getImage()));
            System.out.println("Operacja: ");
        }
    }
}
