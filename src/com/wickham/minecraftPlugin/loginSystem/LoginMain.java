package com.wickham.minecraftPlugin.loginSystem;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashSet;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginMain {

	private static final FileConfiguration PLAYERPASSWORD_CONFIGURATION = WickhamsPlugin.getPlayerPasswordConfig();
	private static final File PLAYERPASSWORD_FILE = WickhamsPlugin.getPlayerPasswordFile();
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
			WickhamsPlugin.getPlayerplayerRegisterStatusConfig().set("playerName." + playerName, passworld);
			WickhamsPlugin.savePlayerplayerRegisterStatusConfig();// 保存信息
			PLAYERPASSWORD_CONFIGURATION.set("playerName." + playerName, MD(passworld));
			savePlayerPasswordConfig();
			return true;
		}
	}

	public static boolean checkPassworldReal(String playerName, String passworld) {// 检查密码
		if (!isRegister(playerName)) {
			return false;
		} else {
			String realPassworld = WickhamsPlugin.getPlayerplayerRegisterStatusConfig()
					.getString("playerName." + playerName);
			if (realPassworld.equals(passworld)) {
				PLAYERPASSWORD_CONFIGURATION.set("playerName." + playerName, MD(passworld));
				savePlayerPasswordConfig();
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean checkPassworldDtell(String passworld) {// 检查密码格式
		if (!(passworld.length() < 5 || passworld.length() > 20)) {
			return true;
		} else
			return false;
	}

	private static void savePlayerPasswordConfig() {
		try {
			PLAYERPASSWORD_CONFIGURATION.save(PLAYERPASSWORD_FILE);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

//	https://cloud.tencent.com/developer/article/1128214
	private static String MD(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("666");
			md.update(s.getBytes("utf-8"));
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}
}
