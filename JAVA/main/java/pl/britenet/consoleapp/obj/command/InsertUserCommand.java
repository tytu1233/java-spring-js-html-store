package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.UserBuilder;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertUserCommand extends Command {

    private final UserService userService;

    public InsertUserCommand(UserService userService) {
        super(Constants.COMMAND_INSERT_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Wprowadź dane użytkownika");

        System.out.println("Wprowadź imię: ");
        String name = scanner.nextLine();
        System.out.println("Wprowadź nazwisko: ");
        String surname = scanner.nextLine();
        System.out.println("Wprowadź login: ");
        String login = scanner.nextLine();
        System.out.println("Wprowadź hasło: ");
        String password = scanner.nextLine();
        System.out.println("Wprowadź adres: ");
        String address = scanner.nextLine();
        System.out.println("Wprowadź telefon: ");
        String phone = scanner.nextLine();

        User user = new UserBuilder()
                .setName(name)
                .setSurname(surname)
                .setLogin(login)
                .setPassword(password)
                .setAddress(address)
                .setPhone(phone)
                .getUser();


            this.userService.insertUser(user);
            System.out.println("Pomyślnie wprowadzono nowego użytkownika!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("Niepowodzenie w dodaniu nowego użytkownika");
        } catch (
        InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane - cena powinna być liczbą!");
        }
    }
}
