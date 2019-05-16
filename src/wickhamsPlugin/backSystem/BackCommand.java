package wickhamsPlugin.backSystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wickhamsPlugin.API.teleport.WTeleport;

public class BackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		// TODO 自动生成的方法存根
		if (sender instanceof Player) {
			if (arg3.length == 0) {
				Player player = (Player) sender;
				if (BackMain.hasBackLocation(player)) {
					WTeleport.teleport(player, BackMain.getOldLocation(player), false);
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "没有位置可以返回");
					return true;
				}
			} else {
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "你必须是个玩家");
			return false;
		}
	}

}
