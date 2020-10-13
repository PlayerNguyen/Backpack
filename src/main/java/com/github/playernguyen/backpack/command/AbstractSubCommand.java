package com.github.playernguyen.backpack.command;

import com.github.playernguyen.backpack.BackpackInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractSubCommand extends BackpackInstance implements Command {

    private final String command;
    private final String args;
    private final String description;
    private final List<String> permissions = new ArrayList<>();
    private final HashMap<String, Command> subCommandMap = new HashMap<>();
    private final AbstractMainCommand mainCommand;

    public AbstractSubCommand(String command, String args, String description, AbstractMainCommand mainCommand) {
        this.command = command;
        this.args = args;
        this.description = description;
        this.mainCommand = mainCommand;
    }

    @Override
    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getArgument() {
        return args;
    }

    public AbstractMainCommand getMainCommand() {
        return mainCommand;
    }



}
