package com.wickham.minecraftPlugin.tpASystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class TpACommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] arg3) {
		if(sender instanceof Player) {
			if (arg3.length==1) {
				Player player=(Player) sender;
				Player targe=Bukkit.getServer().getPlayer(arg3[0]);
				if (targe!=null) {
					if (targe==player) {
						sender.sendMessage(ChatColor.RED+"你不能自己传送到自己");
						return true;
					}else {						
						if (!TpAMain.isRequestFirst(player)){
							TpAMain.newRequest(player, targe);
							player.sendMessage(ChatColor.YELLOW+"请求已发送");
							targe.sendMessage(ChatColor.GREEN+sender.getName()+ChatColor.YELLOW+" 想传送到你这里，请在 "+TpAMain.REQUEST_WAITING_TIME+" 秒内输入 tpayes 确认，否则将忽略");
							Bukkit.getScheduler().runTaskLater(WickhamsPlugin.MAIN,new Runnable() {
								Player thisPlayer=player;
								Player thisTarge=targe;
								@Override
								public void run() {
									// TODO 自动生成的方法存根
									if(TpAMain.isRequestFirst(thisPlayer)) {
										TpAMain.cancelRequest(thisPlayer);
										thisTarge.sendMessage(ChatColor.RED+"已拒绝 "+thisPlayer.getName()+" 的请求");
										thisPlayer.sendMessage(ChatColor.RED+"请求已超时");
									}
									return;
								}
								
							}, TpAMain.REQUEST_WAITING_TIME*20);
							return true;
						}else {
							player.sendMessage(ChatColor.RED+"你的操作过于频繁，请在稍后再试");
							return true;
						}
					}
				}else {
					sender.sendMessage(ChatColor.RED+"目标玩家不存在或不在线");
					return false;
				}
			}else {
				return false;
			}
		}else {
			sender.sendMessage(ChatColor.RED+"你必须是个玩家");
			return false;
		}
	}
}