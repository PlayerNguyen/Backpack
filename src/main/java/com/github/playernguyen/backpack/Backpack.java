package com.github.playernguyen.backpack;

import com.github.playernguyen.backpack.command.BackpackCommand;
import com.github.playernguyen.backpack.command.CommandManager;
import com.github.playernguyen.backpack.config.BackpackConfig;
import com.github.playernguyen.backpack.language.BackpackLanguage;
import com.github.playernguyen.backpack.listener.BackpackListener;
import com.playernguyen.playernguyencore.listener.CoreListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class Backpack extends JavaPlugin {

    private static Backpack backpack;

    private BackpackLanguage language;
    private BackpackConfig configuration;
    private CoreListenerManager listeners;
    private CommandManager commandManager;
    private LinkedHashMap<UUID, UUID> playerOpeningMap = new LinkedHashMap<>();

    @Override
    public void onEnable() {

        setupInstance();

        // Language load
        if (!setupLanguage()) {
            getLogger().info("Cannot save the language file...Disable Backpack now");
            disable();
            return;
        }

        // Configuration load
        if (!setupConfiguration()) {
            getLogger().info("Cannot save config file. Disable Backpack now");
            disable();
            return;
        }

        // Listener setup
        setupListener();

        // Commands setup
        setupCommand();
    }

    private void setupCommand() {
        this.commandManager = new CommandManager();
        // Register commands
        getCommandManager().put("backpack", new BackpackCommand());

        // Handler this ( register all )
        getCommandManager().registerAll();
    }

    private void setupListener() {
        this.listeners = new CoreListenerManager(this, true);
        /* ------------------- */
        getListeners().add(new BackpackListener());
    }

    private void setupInstance() {
        backpack = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupConfiguration() {
        try {
            this.configuration = new BackpackConfig(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean setupLanguage() {
        try {
            this.language = new BackpackLanguage(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    void disable() {
        getLogger().info("Force disabling plugin...");
        Bukkit.getPluginManager().disablePlugin(this);
    }


    public BackpackConfig getConfiguration() {
        return configuration;
    }

    public BackpackLanguage getLanguage() {
        return language;
    }

    public static Backpack getBackpack() {
        return backpack;
    }

    public CoreListenerManager getListeners() {
        return listeners;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public LinkedHashMap<UUID, UUID> getPlayerOpeningMap() {
        return playerOpeningMap;
    }
}
