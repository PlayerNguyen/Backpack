package com.github.playernguyen.backpack.command;

import com.github.playernguyen.backpack.language.BackpackLanguageFlags;

public enum CommandResult {

    NOT_PERMISSION(BackpackLanguageFlags.COMMAND_RESULT_NOT_PERMISSION),
    INVALID_SENDER(BackpackLanguageFlags.COMMAND_RESULT_INVALID_SENDER),
    NOTHING(null)
    ;

    private final BackpackLanguageFlags languageFlags;

    CommandResult(BackpackLanguageFlags languageFlags) {
        this.languageFlags = languageFlags;
    }

    public BackpackLanguageFlags getLanguageFlags() {
        return languageFlags;
    }
}
