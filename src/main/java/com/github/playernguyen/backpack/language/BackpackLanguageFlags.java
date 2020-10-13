package com.github.playernguyen.backpack.language;

import com.playernguyen.playernguyencore.config.CoreConfigFlag;

public enum BackpackLanguageFlags implements CoreConfigFlag {

    COMMAND_RESULT_NOT_PERMISSION("Command.NotPermission",
            "&cYou are not having permissions to do this"),
    COMMAND_RESULT_MISSING_ARGUMENTS("Command.MissingArguments",
            "&cMissing arguments: &6/backpack <command>"),

    COMMAND_RESULT_PLAYER_NOT_FOUND("Command.PlayerNotFound", "&cPlayer not found!"),
    COMMAND_RESULT_INVALID_ITEM("Command.InvalidItem", "&cInvalid item."),
    COMMAND_RESULT_INVALID_SENDER("Command.InvalidSender", "&cInvalid sender. This command was designed for a specific sender!"),

    COMMAND_RESULT_NOT_FOUND("Command.NotFound", "&cCommand not found") ,
    PREFIX("Preferences.Prefix", "&7[&cBackpack&7]")

    ;

    private final String path;
    private final Object obDef;

    BackpackLanguageFlags(String path, Object obDef) {
        this.path = path;
        this.obDef = obDef;
    }

    @Override
    public Object getDefault() {
        return obDef;
    }

    @Override
    public String getPath() {
        return path;
    }
}
