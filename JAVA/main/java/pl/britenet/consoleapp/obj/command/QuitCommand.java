package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;

import static pl.britenet.consoleapp.Main.IS_ON;

public class QuitCommand extends Command {

    public QuitCommand() {
        super(Constants.COMMAND_NAME_QUIT);
    }

    @Override
    public void execute() {
        System.out.println("Wyłączanie aplikacji");
        IS_ON = false;
    }
}
