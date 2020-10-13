package com.github.playernguyen.backpack.core;

import com.playernguyen.playernguyencore.config.CoreConfigFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum BackpackFlags implements CoreConfigFlag {

    ID("id", ""),
    CONTENT("content", new HashMap<Integer, ItemStack>())
    ;

    private final String path;
    private final Object aDefault;

    BackpackFlags(String path, Object aDefault) {
        this.path = path;
        this.aDefault = aDefault;
    }

    @Override
    public Object getDefault() {
        return aDefault;
    }

    @Override
    public String getPath() {
        return path;
    }
}
