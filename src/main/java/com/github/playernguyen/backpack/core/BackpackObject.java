package com.github.playernguyen.backpack.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class BackpackObject {

    private final UUID unique;
    private final Map<Integer, ItemStack> contents;

    public BackpackObject(UUID unique, Map<Integer, ItemStack> contents) {
        this.unique = unique;
        this.contents = contents;
    }

    public UUID getUnique() {
        return unique;
    }

    public Map<Integer, ItemStack> getContents() {
        return contents;
    }

    public void open(Player inventoryHolder) {
        Inventory inventory = Bukkit.createInventory(inventoryHolder, 45,
                this.unique.toString());
        for (Integer i : contents.keySet()) {
            inventory.setItem(i, contents.get(i));
        }
        inventoryHolder.openInventory(inventory);
    }

}
