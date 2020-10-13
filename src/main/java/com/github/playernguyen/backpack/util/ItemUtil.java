package com.github.playernguyen.backpack.util;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemUtil {

    public static Map<String, Object> readNBT(ItemStack stack) {
        net.minecraft.server.v1_12_R1.ItemStack asNMSCopy = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound wrapper = (asNMSCopy.hasTag()) ? asNMSCopy.getTag() : new NBTTagCompound();

        Map<String, Object> map = new LinkedHashMap<>();

        assert wrapper != null;
        for (String key : wrapper.c()) {
            map.put(key, wrapper.get(key));
        }

        return map;
    }

    public static ItemStack writeNBT(ItemStack stack, Map<String, Object> data) {
        net.minecraft.server.v1_12_R1.ItemStack asNMSCopy = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound wrapper = (asNMSCopy.hasTag()) ? asNMSCopy.getTag() : new NBTTagCompound();
        for (String key : data.keySet()) {
            assert wrapper != null;
            wrapper.setString(key, String.valueOf(data.get(key)));
        }
        asNMSCopy.setTag(wrapper);
        return CraftItemStack.asBukkitCopy(asNMSCopy);
    }

    public static ItemStack insertNBT(ItemStack stack, String key, String value) {
        Map<String, Object> stringObjectMap = readNBT(stack);
        stringObjectMap.put(key, value);
        return writeNBT(stack, stringObjectMap);
    }

    public static ItemStack insertNBT(ItemStack stack, Map<String, Object> data) {
        Map<String, Object> stringObjectMap = readNBT(stack);

        stringObjectMap.putAll(data);

        return writeNBT(stack, stringObjectMap);
    }

}
