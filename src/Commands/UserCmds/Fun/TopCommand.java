package Commands.UserCmds.Fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class TopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Top"))) {
                Utils.teleport(player, player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0, 1.5, 0));
                return true;
            } else {
                Utils.chat(player, messages.getString("NoPerms"));
            }
        } else {
            Utils.chat(sender, messages.getString("SenderNotPlayer"));
        }
        return false;
    }
}
//Found an easier way to do the command, Thanks to @Lokka30! <3