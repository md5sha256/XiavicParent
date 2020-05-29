package com.github.xiavic.essentials.Commands.player.Essential;

import com.github.xiavic.essentials.Main;
import com.github.xiavic.essentials.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DisposeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null, 54, "Chest");
            if (player.hasPermission(Main.permissions.getString("Dispose")) || player.isOp()) {
                player.openInventory(inventory);
            } else {
                Utils.chat(player, Main.messages.getString("NoPerms"));
            }
        } else {
            Utils.chat(sender, Main.messages.getString("SenderNotPlayer"));
        }
        return false;
    }
}
