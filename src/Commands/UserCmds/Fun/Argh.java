package Commands.UserCmds.Fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import utils.Files.Messages;
import utils.Files.Permissions;
import utils.Utils;

import static Main.mainClass.messages;
import static Main.mainClass.permissions;

public class Argh implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(permissions.getString("Argh")) || player.isOp()) {
                if (command.getName().equalsIgnoreCase("Argh")) {
                    player.sendMessage(Utils.chat(messages.getString("Argh")));
                }
                return true;
            }
        }
        return false;
    }
}
