package com.github.playernguyen.backpack.config;

import com.playernguyen.playernguyencore.config.CoreConfigFlag;
import org.bukkit.Material;

public enum  BackpackConfigurationFlags implements CoreConfigFlag {

    ITEM_BACKPACK_MATERIAL("item.backpack.material", Material.ENDER_CHEST.toString()),
    ITEM_BACKPACK_DISPLAY("item.backpack.display", "&cBackpack"),
    ITEM_BACKPACK_LORE("item.backpack.lore", "&cBackpack|"),
    ;

    private final String path;
    private final Object obDef;

    BackpackConfigurationFlags(String path, Object obDef) {
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
