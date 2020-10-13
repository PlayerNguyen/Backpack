package com.github.playernguyen.backpack.core;

import com.github.playernguyen.backpack.Backpack;
import com.github.playernguyen.backpack.config.BackpackConfigurationFlags;
import com.github.playernguyen.backpack.util.ItemUtil;
import com.github.playernguyen.backpack.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class BackpackItem {

    public static final String KEY_BACKPACK_ID = "backpack_id";

    public static InventoryBag createBackpackItem() {
        Backpack backpackPlugin = Backpack.getBackpack();
        ItemStack stack = new ItemStack(
                Material
                        .getMaterial(backpackPlugin.getConfiguration()
                                .getString(BackpackConfigurationFlags.ITEM_BACKPACK_MATERIAL)),
                1
        );
        // set data
        ItemMeta itemMeta = stack.getItemMeta();
        if (itemMeta != null) {
            // set display
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    backpackPlugin.getConfiguration()
                            .getString(BackpackConfigurationFlags.ITEM_BACKPACK_DISPLAY)));
            itemMeta.setLore(StringUtil.toList(backpackPlugin.getConfiguration()
                    .getString(BackpackConfigurationFlags.ITEM_BACKPACK_LORE)));
        }
        stack.setItemMeta(itemMeta);
        UUID uuid = UUID.randomUUID();
        return new InventoryBag(uuid, ItemUtil.insertNBT(stack, KEY_BACKPACK_ID, uuid.toString()));
    }

    public static InventoryBag getBackItem(ItemStack stack) {
        Map<String, Object> stringObjectMap = ItemUtil.readNBT(stack);
        if (stringObjectMap.get(KEY_BACKPACK_ID) == null) {
            return null;
        }
        // Get uuid and then return
        UUID uuid = UUID.fromString((String) stringObjectMap.get(KEY_BACKPACK_ID));
        return new InventoryBag(uuid, stack);
    }

    public static boolean isBackpack(ItemStack stack) {
        Map<String, Object> stringObjectMap = ItemUtil.readNBT(stack);
        return stringObjectMap.get(KEY_BACKPACK_ID) != null;
    }

    public static class InventoryBag {
        private final UUID unique;
        private final ItemStack stack;

        public InventoryBag(UUID unique, ItemStack stack) {
            this.unique = unique;
            this.stack = stack;
        }

        public UUID getUnique() {
            return unique;
        }

        public ItemStack getStack() {
            return stack;
        }

        public BackpackObject loadObjectFromConfigs() throws IOException {
//            return new BackpackConfiguration(Backpack.getBackpack(), getUnique()).loadObject();
            return null;
        }
    }



}
