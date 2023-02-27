package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.Pagination;
import pl.britenet.consoleapp.service.UserService;

import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllUserCommand extends Command {

    private final UserService userService;


    public GetAllUserCommand(UserService userService) {
        super(Constants.COMMAND_GET_ALL_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {
        ON = true;
        Scanner scanner = new Scanner(System.in);
        Pagination<User> paginate = new Pagination<>(this.userService.getAllUsers().get());
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
                paginate.getRecords(command).forEach(user -> System.out.println("ID: " + user.getId() + ", imie: " + user.getName() + ", nazwisko: " + user.getSurname() + ", login: " + user.getLogin() + ", hasło: " + user.getPassword() + ", adres: " + user.getAddress() + ", telefon: " + user.getPhone()));
                System.out.println("Operacja: ");
        }
    }
}
