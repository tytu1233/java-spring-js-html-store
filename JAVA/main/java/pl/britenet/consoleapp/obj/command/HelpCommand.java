package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.service.CommandService;

public class HelpCommand extends Command {

    private final CommandService commandService;

    public HelpCommand(CommandService commandService) {
        super(Constants.COMMAND_NAME_HELP);
        this.commandService = commandService;
    }

    @Override
    public void execute() {
        System.out.println("Wszystkie dostepne komendy:");
        for (Command command : this.commandService.getCommandList()) {
            System.out.println(String.format("- %s", command.getName()));
        }
    }
}
