package com.github.playernguyen.backpack.command;

import com.github.playernguyen.backpack.BackpackInstance;
import com.github.playernguyen.backpack.language.BackpackLanguageFlags;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractMainCommand extends BackpackInstance
        implements Command, TabExecutor {

    private final String command;
    private final String args;
    private final String description;
    private final List<String> permissions = new ArrayList<>();
    private final HashMap<String, Command> subCommandMap = new HashMap<>();

    public AbstractMainCommand(String command, String args, String description) {
        this.command = command;
        this.args = args;
        this.description = description;
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

    @Override
    public boolean onCommand(CommandSender commandSender,
                             org.bukkit.command.Command command,
                             String s, String[] strings) {
        // Permissions check
        if (!hasPermission(commandSender)) {
            commandSender.sendMessage(getLanguage().getPrefixedLanguage(BackpackLanguageFlags.COMMAND_RESULT_NOT_PERMISSION));
            return true;
        }
        // Missing args
        if (strings.length < 1) {
            commandSender.sendMessage(
                    getLanguage().getPrefixedLanguage(BackpackLanguageFlags.COMMAND_RESULT_MISSING_ARGUMENTS)
            );
            return true;
        }

        // Not found command, found valid (allow) command and print out
        if (!subCommandMap.containsKey(strings[0]) || strings[0].equalsIgnoreCase("?")) {
            if (!strings[0].equalsIgnoreCase("?")) {
                commandSender.sendMessage(
                        getLanguage().getPrefixedLanguage(BackpackLanguageFlags.COMMAND_RESULT_NOT_FOUND)
                );
            }
            commandSender.sendMessage(ChatColor.GRAY + "[----------------------]");
            subCommandMap.forEach(((str, com) -> {
                if (com.hasPermission(commandSender)) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&7/backpack &6%s %s: %s",
                            com.getCommand(), com.getArgument(), com.getDescription())));
                }
            }));
            return true;
        }

        // Get command then execute
        Command responseCommand = subCommandMap.get(strings[0]);
        CommandResult commandResult = responseCommand.onExecute(commandSender, Arrays.asList(strings).subList(1, strings.length));
        // Send if not null
        BackpackLanguageFlags languageFlags = commandResult.getLanguageFlags();
        onExecute(commandSender, Arrays.asList(strings));
        if (languageFlags != null) {
            commandSender.sendMessage(getLanguage().getPrefixedLanguage(languageFlags));
        }
        return true;
    }

    public void registerPermission(String... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
    }

    public HashMap<String, Command> getSubCommandMap() {
        return subCommandMap;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        // Find command
        if (strings.length > 0) return (subCommandMap.containsKey(strings[0])) ?
                subCommandMap.get(strings[0]).tabList(commandSender, Arrays.asList(strings).subList(1, strings.length - 1)) : null;
        return new ArrayList<>(subCommandMap.keySet());
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {
        return CommandResult.NOTHING;
    }
}
