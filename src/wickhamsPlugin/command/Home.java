package wickhamsPlugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wickhamsPlugin.API.teleport.WTeleport;

public class Home implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (arg3.length == 0) {
				if (player.getBedSpawnLocation() == null) {
					player.sendMessage(ChatColor.RED + "你还没在床上睡过觉，请先睡一夜，或者使用 sethome 设置家");
					return true;
				} else {
					WTeleport.teleport(player, player.getBedSpawnLocation(), true);
					return true;
				}
			} else
				return false;
		} else {
			sender.sendMessage(ChatColor.RED + "你必须是个玩家");
			return false;
		}
	}

}
