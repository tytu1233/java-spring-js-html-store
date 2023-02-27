package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class GetUserCommand extends Command {

    private final UserService userService;

    public GetUserCommand(UserService userService) {
        super(Constants.COMMAND_GET_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID użytkownika, którego chcesz wyświetlić:");
        int id = scanner.nextInt();

        Optional<User> optionalUser = this.userService.getUser(id);


        if (optionalUser.isPresent()) {
            System.out.println("Szczegóły użytkownika: \n");
            System.out.println("ID: " + optionalUser.get().getId());
            System.out.println("Imie: " + optionalUser.get().getName());
            System.out.println("Nazwisko: " + optionalUser.get().getSurname());
            System.out.println("Login: " + optionalUser.get().getLogin());
            System.out.println("Hasło: " + optionalUser.get().getPassword());
            System.out.println("Adres: " + optionalUser.get().getAddress());
            System.out.println("Telefon: " + optionalUser.get().getPhone());
        }
        else {
            System.out.println("Użytkownik o takim ID nie istnieje");
        }
    }
}
