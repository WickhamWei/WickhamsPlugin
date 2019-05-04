package wickhamsPlugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hello implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		/*
		 * 其中sender为命令发送者, 必定是Player实例或ConsoleSender实例. cmd Command实例,暂时无用. label
		 * 相当于主命令去掉/ ,如输入/gamemode 1,则label参数就为"gamemode". args 命令参数数组,如输入/gamemode 1
		 * test 则args={"1","test"}.
		 */
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.BLUE + "你好！我是 Wickham");
			return true;
		} else {
			sender.sendMessage("辛苦腐竹了");
			return true;
		}
	}
}
