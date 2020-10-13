package com.github.playernguyen.backpack.core;

import com.github.playernguyen.backpack.Backpack;
import com.github.playernguyen.backpack.BackpackInstance;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BackpackConfiguration extends BackpackInstance {

    private static final String DATA_FOLDER = "data";

    private final UUID unique;
    private final File file;
    private final FileConfiguration fileConfiguration;

    public BackpackConfiguration(UUID unique) {
        this.unique = unique;
        File parent = new File(getBackpack().getDataFolder(), DATA_FOLDER);
        if (!parent.exists() && !parent.mkdir())
            throw new IllegalStateException("Cannot found and create parent folder: " + parent.getPath());
        this.file = new File(parent, this.unique.toString() + ".yml");
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
        // Save file
        // Save empty map
        if (!this.file.exists())
            save(emptyHashmap());
    }

    public static File getDataFolder() {
        return new File(Backpack.getBackpack().getDataFolder(), "/data");
    }

    public UUID getUnique() {
        return unique;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public BackpackObject getBackpackObject() {
        if (!this.file.exists()) {
            return new BackpackObject(this.unique, emptyHashmap());
        }
        // Scan the item
        return new BackpackObject(this.unique, loadMap());
    }

    public HashMap<Integer, ItemStack> emptyHashmap() {
        HashMap<Integer, ItemStack> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(0, new ItemStack(Material.AIR, 1));
        return objectObjectHashMap;
    }

    public void saveMap(Map<Integer, ItemStack> stackMap) {
        stackMap.forEach((a, b)-> {
            this.fileConfiguration.set("contents." + a, b);
        });
    }

    public Map<Integer, ItemStack> loadMap() {
        Map<Integer, ItemStack> stack = new HashMap<>();
        Set<String> contents = this.fileConfiguration.getConfigurationSection("contents").getKeys(false);
        contents.forEach(e -> stack.put(Integer.valueOf(e), this.fileConfiguration.getItemStack("contents." + e)));
        return stack;
    }

    public void save (HashMap<Integer, ItemStack> map) {
        // Save empty map
        saveMap(map);
        // Try catch block to save
        try {
            this.fileConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
