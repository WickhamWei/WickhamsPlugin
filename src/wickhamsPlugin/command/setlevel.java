package wickhamsPlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLevel implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] allargStrings) {
		// TODO 自动生成的方法存根
		if(allargStrings.length==2) {
			if(sender instanceof Player) {
				int targeLevel=Integer.parseInt(allargStrings[1]);
				Player targePlayer=Bukkit.getServer().getPlayer(allargStrings[0]);
				if(targePlayer!=null) {
					targePlayer.setLevel(targeLevel);
				}
			}else {
				int targeLevel=Integer.parseInt(allargStrings[1]);
				Player targePlayer=Bukkit.getServer().getPlayer(allargStrings[0]);
				if(targePlayer!=null) {
					targePlayer.setLevel(targeLevel);
				}
				return true;
			}
		}
		return false;
	}
	
}
