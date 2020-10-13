package com.github.playernguyen.backpack.config;

import com.playernguyen.playernguyencore.config.CoreConfig;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class BackpackConfig extends CoreConfig<BackpackConfigurationFlags> {

    public BackpackConfig(Plugin plugin) throws IOException {
        super(plugin, "config.yml", BackpackConfigurationFlags.values(), "");
    }

    @Override
    public void onLoad() {

    }
}
