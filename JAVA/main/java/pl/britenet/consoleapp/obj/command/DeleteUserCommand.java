package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.UserBuilder;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteUserCommand extends Command {

    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        super(Constants.COMMAND_DELETE_USER);
        this.userService = userService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        try {
        System.out.println("Podaj ID użytkownika, którego chcesz usunąć: ");

        int id = scanner.nextInt();

        if(this.userService.getUser(id).isEmpty()) {
            System.out.println("Nie ma rekordu o takim ID");
            return;
        }


        User user = new UserBuilder(id)
                .getUser();

        this.userService.deleteUser(user);
        System.out.println("Pomyślnie usunięto użytkownika o ID: " + id);
        } catch (IllegalStateException e) {
            System.out.println("Nie można usunąć tego rekordu znajduje się w innej tabeli");
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono niepoprawne dane!");
        }
    }
}
