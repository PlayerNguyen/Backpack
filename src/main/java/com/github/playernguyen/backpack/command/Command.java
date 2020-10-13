package com.github.playernguyen.backpack.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface Command {

    CommandResult onExecute(CommandSender sender, List<String> args);

    List<String> tabList(CommandSender sender, List<String> args);

    List<String> getPermissions();

    String getCommand();

    String getArgument();

    String getDescription();

    default boolean hasPermission(CommandSender sender) {
        if (getPermissions().size() < 1 ) return true;
        return getPermissions().stream().anyMatch(sender::hasPermission);
    }

}
