package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.UserBuilder;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.UserService;

import java.util.Scanner;

public class UpdateUserCommand extends Command {

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        super(Constants.COMMAND_UPDATE_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID użytkownika, które chcesz zaktualizować: ");
        int id = scanner.nextInt();

        if(this.userService.getUser(id).isEmpty()) {
            System.out.println("Użytkownik o takim ID nie istnieje");
            return;
        }

        System.out.println("Wprowadź imię: ");
        scanner.nextLine();
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

        User user = new UserBuilder(id)
                .setName(name)
                .setSurname(surname)
                .setLogin(login)
                .setPassword(password)
                .setAddress(address)
                .setPhone(phone)
                .getUser();


        try {
            this.userService.updateUser(user);
            System.out.println("Pomyślnie zaktualizowano użytkownika!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
