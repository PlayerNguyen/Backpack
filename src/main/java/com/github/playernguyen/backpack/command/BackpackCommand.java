package com.github.playernguyen.backpack.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public class BackpackCommand extends AbstractMainCommand {

    public BackpackCommand() {
        super("backpack", "<command>", "Backpack main commands");
        // Register commands
        this.getSubCommandMap().put("give", new BackpackCommandGive(this));
        this.getSubCommandMap().put("id", new BackpackCommandId(this));
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {
        return CommandResult.NOTHING;
    }

    @Override
    public List<String> tabList(CommandSender sender, List<String> args) {
        return null;
    }
}
