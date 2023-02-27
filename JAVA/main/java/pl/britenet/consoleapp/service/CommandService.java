package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandService {

    private final List<Command> commandList;

    public CommandService() {
        this.commandList = new ArrayList<>();
    }

    public Optional<Command> findCommand(String name) {
        return commandList.stream()
                .filter( command -> command.getName().equals(name))
                .findFirst();
    }

    public void registerCommand(Command command) {
        this.commandList.add(command);
    }

    public List<Command> getCommandList() {
        return commandList;
    }
}
