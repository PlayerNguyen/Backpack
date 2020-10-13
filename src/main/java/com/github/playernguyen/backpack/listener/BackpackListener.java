package com.github.playernguyen.backpack.listener;

import com.github.playernguyen.backpack.Backpack;
import com.github.playernguyen.backpack.config.BackpackConfig;
import com.github.playernguyen.backpack.core.BackpackConfiguration;
import com.github.playernguyen.backpack.core.BackpackItem;
import com.github.playernguyen.backpack.core.BackpackObject;
import com.github.playernguyen.backpack.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.*;

public class BackpackListener implements Listener {


    @EventHandler
    public void onDestroy(EntityDamageEvent e) {
        if (e.getEntity() instanceof Item) {
            Map<String, Object> map = ItemUtil.readNBT(((Item) e.getEntity()).getItemStack());
            System.out.println(map);
        }
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack != null) {
            Map<String, Object> result = ItemUtil.readNBT(stack);
            if (result.containsKey(BackpackItem.KEY_BACKPACK_ID)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) throws IOException {
        Player player = e.getPlayer();
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack != null) {
            Map<String, Object> result = ItemUtil.readNBT(stack);
            if (result.containsKey(BackpackItem.KEY_BACKPACK_ID)) {
                // UUID
                String replacement = result.get(BackpackItem.KEY_BACKPACK_ID).toString().replace("\"", "");
                UUID uuid = UUID.fromString(replacement);

                BackpackConfiguration backpackConfiguration = new BackpackConfiguration(uuid);
                BackpackObject backpackObject = backpackConfiguration.getBackpackObject();
                backpackObject.open(player);
                // Set opening
                Backpack.getBackpack().getPlayerOpeningMap().put(player.getUniqueId(), uuid);
            }
        }
    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        HashMap<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            map.put(i, stack);
        }
        // Save changes
        BackpackConfiguration backpackConfiguration = new BackpackConfiguration(
                Backpack.getBackpack().getPlayerOpeningMap().get(player.getUniqueId())
        );
        backpackConfiguration.save(map);

        // Remove player from opening
        Backpack.getBackpack().getPlayerOpeningMap().remove(player.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (Backpack.getBackpack().getPlayerOpeningMap().containsKey(e.getPlayer().getUniqueId())) {
            Player player = e.getPlayer();
            Inventory inventory = e.getPlayer().getOpenInventory().getTopInventory();
            HashMap<Integer, ItemStack> map = new HashMap<>();
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                map.put(i, stack);
            }
            // Save changes
            BackpackConfiguration backpackConfiguration = new BackpackConfiguration(
                    Backpack.getBackpack().getPlayerOpeningMap().get(player.getUniqueId())
            );
            backpackConfiguration.save(map);

            // Remove player from opening
            Backpack.getBackpack().getPlayerOpeningMap().remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerInteractInventory(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getAction() == InventoryAction.PICKUP_ALL || e.getAction() == InventoryAction.PICKUP_HALF
        || e.getAction() == InventoryAction.PICKUP_ONE || e.getAction() == InventoryAction.PICKUP_SOME) {
            if (BackpackItem.isBackpack(e.getCurrentItem())) {
                e.setCancelled(true);
            }
        }
    }

}
