package com.github.playernguyen.backpack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

import java.util.HashMap;

public class CommandManager  {

    private final HashMap<String, AbstractMainCommand> map = new HashMap<>();

    public Command put(String command, AbstractMainCommand object) {
        return map.put(command, object);
    }

    public HashMap<String, AbstractMainCommand> getMap() {
        return map;
    }

    public void registerAll() {
        map.forEach((s, command) -> {
            // Find plugin comma
            PluginCommand pluginCommand = Bukkit.getPluginCommand(s);
            // If this to null
            if (pluginCommand == null) {
                throw new NullPointerException("Unregister plugin call: " + s);
            }
            // If not
            pluginCommand.setExecutor(command);
        });
    }
}
