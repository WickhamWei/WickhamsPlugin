package wickhamsPlugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
		// TODO 自动生成的方法存根
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				if (args.length == 0) {
					player.getWorld().setSpawnLocation(player.getLocation());
					player.sendMessage(ChatColor.YELLOW+"已将该世界的出生地设为你的位置");
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "你必须是个玩家");
			return false;
		}
	}

}
