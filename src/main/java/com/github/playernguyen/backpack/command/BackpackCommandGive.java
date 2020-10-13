package com.github.playernguyen.backpack.command;

import com.github.playernguyen.backpack.core.BackpackItem;
import com.github.playernguyen.backpack.language.BackpackLanguageFlags;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BackpackCommandGive extends AbstractSubCommand{

    public BackpackCommandGive(Command main) {
        super("give", "", "Give new backpack to player", (AbstractMainCommand) main);
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {
        Player target;
        if (sender instanceof Player) {
            target = (args.size() < 1) ? (Player) sender : Bukkit.getPlayerExact(args.get(0));
        } else {
            target = (args.size() < 1) ? null : Bukkit.getPlayerExact(args.get(0));
        }

        // Not found target
        if (target == null) {
            sender.sendMessage(getLanguage()
                    .getPrefixedLanguage(BackpackLanguageFlags.COMMAND_RESULT_PLAYER_NOT_FOUND));
            return CommandResult.NOTHING;
        }

        // Create pack
        BackpackItem.InventoryBag backpackItem = BackpackItem.createBackpackItem();

        // Add to inventory
        target.getInventory().addItem(backpackItem.getStack());

        return CommandResult.NOTHING;
    }

    @Override
    public List<String> tabList(CommandSender sender, List<String> args) {
        return null;
    }
}
