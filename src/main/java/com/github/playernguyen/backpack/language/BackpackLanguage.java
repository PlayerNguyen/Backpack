package com.github.playernguyen.backpack.language;

import com.playernguyen.playernguyencore.config.CoreConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class BackpackLanguage extends CoreConfig<BackpackLanguageFlags> {

    public BackpackLanguage(Plugin plugin) throws IOException {
        super(plugin, "language.yml", BackpackLanguageFlags.values(), "");
    }

    public String getFormattedLanguage(BackpackLanguageFlags flags) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                getFileConfiguration().getString(flags.getPath())
        );
    }

    public String getPrefixedLanguage(BackpackLanguageFlags flags) {
        return getFormattedLanguage(BackpackLanguageFlags.PREFIX) + " " + getFormattedLanguage(flags);
    }

    @Override
    public void onLoad() {

    }
}
