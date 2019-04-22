package com.wickham.minecraftPlugin.loginSystem;

import java.util.HashSet;

import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginMain {
	public static HashSet<String> unLoginList = new HashSet<String>();

	public static void newPlayer(Player player) {// 新建玩家
		unLoginList.add(player.getName());
	}

	public static boolean isLogin(Player player) {// 是否登陆
		return !unLoginList.contains(player.getName());
	}
	
	public static boolean isLogin(String playerNameString) {// 是否登陆
		return !unLoginList.contains(playerNameString);
	}

	public static void playerLogin(Player player) {// 设置登陆状态
		unLoginList.remove(player.getName());
	}
	
	public static void playerLogin(String playerNameString) {// 设置登陆状态
		unLoginList.remove(playerNameString);
	}

	public static boolean isRegister(String playerName) {// 是否注册
		if (WickhamsPlugin.getPlayerplayerRegisterStatusConfig().contains("playerName." + playerName)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean register(String playerName, String passworld) {// 注册
		if (isRegister(playerName)) {
			return true;
		} else {
			WickhamsPlugin.getPlayerplayerRegisterStatusConfig().set("playerName." + playerName,passworld);
			WickhamsPlugin.savePlayerplayerRegisterStatusConfig();// 保存信息
			return true;
		}
	}

	public static boolean checkPassworldReal(String playerName, String passworld) {// 检查密码
		if (!isRegister(playerName)) {
			return false;
		} else {
			String realPassworld = WickhamsPlugin.getPlayerplayerRegisterStatusConfig()
					.getString("playerName." + playerName);
			return realPassworld.equals(passworld);
		}
	}

	public static boolean checkPassworldDtell(String passworld) {// 检查密码格式
		if (!(passworld.length() < 5 || passworld.length() > 20)) {
			return true;
		} else
			return false;
	}
}
