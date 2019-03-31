package com.wickham.minecraftPlugin.loginSystem;

import java.util.HashMap;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginMain {
	public static HashMap<String, Boolean> LoginList = new HashMap<String, Boolean>();

	public static void newPlayer(String playerName) {// 新建玩家
		LoginList.put(playerName, false);
	}

	public static boolean isLogin(String playerName) {// 是否登陆
		return LoginList.get(playerName);
	}

	public static void setPlayerLoginStatus(String playerName, boolean flag) {// 设置登陆状态
		LoginList.put(playerName, flag);
	}

	public static void removePlayer(String playerName) {// 从Map移除玩家
		LoginList.remove(playerName);
	}

	public static void cleanPlayerList() {// 清理缓存-没毛用
		LoginList.clear();
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
