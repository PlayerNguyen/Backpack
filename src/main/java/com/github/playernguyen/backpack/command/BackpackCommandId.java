package com.github.playernguyen.backpack.command;

import com.github.playernguyen.backpack.core.BackpackItem;
import com.github.playernguyen.backpack.language.BackpackLanguageFlags;
import com.github.playernguyen.backpack.util.ItemUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class BackpackCommandId extends AbstractSubCommand {


    public BackpackCommandId(AbstractMainCommand mainCommand) {
        super("id", "", "Get uuid of back pack on player hand", mainCommand);
    }

    @Override
    public CommandResult onExecute(CommandSender sender, List<String> args) {
        // Not a player
        if (!(sender instanceof Player)) {
            return CommandResult.INVALID_SENDER;
        }

        Player player = (Player) sender;
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack != null) {
            Map<String, Object> result = ItemUtil.readNBT(stack);
            if (result.containsKey(BackpackItem.KEY_BACKPACK_ID)) {
                player.sendMessage(result.get(BackpackItem.KEY_BACKPACK_ID).toString());
            }
        } else {
            getLanguage().getPrefixedLanguage(BackpackLanguageFlags.COMMAND_RESULT_INVALID_ITEM);
        }

        return CommandResult.NOTHING;
    }

    @Override
    public List<String> tabList(CommandSender sender, List<String> args) {
        return null;
    }
}
