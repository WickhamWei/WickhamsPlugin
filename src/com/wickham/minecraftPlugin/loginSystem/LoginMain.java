package com.wickham.minecraftPlugin.loginSystem;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.wickham.minecraftPlugin.WickhamsPlugin;

public class LoginMain {
	private static final Plugin WICKHAMS_PLUGIN=WickhamsPlugin.MAIN;
	private static File playerPasswordFile;
	private static FileConfiguration playerPasswordConfig;
	
	public static void createPlayerPasswordConfig() {
		playerPasswordFile=new File(WICKHAMS_PLUGIN.getDataFolder(), "playerPassword.yml");
		if(!playerPasswordFile.exists()) {
			playerPasswordFile.getParentFile().mkdirs();
            WICKHAMS_PLUGIN.saveResource("playerPassword.yml", false);
		}
		playerPasswordConfig= new YamlConfiguration();
        try {
            playerPasswordConfig.load(playerPasswordFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        playerPasswordConfig.addDefault("一般情况下禁止修改此文件，文件出错可能导致数据丢失", "此文件保存了玩家的密码");
        playerPasswordConfig.options().copyDefaults(true);// 如果没有看到上面的内容，拷贝一个进去
        savePlayerPasswordConfig();
	}
	
	public static FileConfiguration getPlayerPasswordConfig() {
        return playerPasswordConfig;
    }
	
	public static File getPlayerPasswordFile() {
        return playerPasswordFile;
    }
	
	public static boolean copyOldPasswordFile() {
		File playerRegisterStatusConfigFile = new File(WickhamsPlugin.MAIN.getDataFolder(),
				"playerRegisterStatusConfig.yml");
		if (!playerRegisterStatusConfigFile.exists()) {
			return false;
		} else {
			FileConfiguration playerOldPasswordConfiguration=YamlConfiguration.loadConfiguration(playerRegisterStatusConfigFile);
			List<String> playerList=playerOldPasswordConfiguration.getStringList("playerName");
			for(String playerName:playerList) {
				String password = playerOldPasswordConfiguration.getString("playerName." + playerName);
				playerPasswordConfig.set("playerName." + playerName, encryptPassword(password));
				savePlayerPasswordConfig();
			}
			return true;
		}
	}

	private static HashSet<String> unLoginList = new HashSet<String>();

	public static void newPlayer(Player player) {// 新建玩家
		unLoginList.add(player.getName());
	}
	
	public static void newPlayer(String playerNameString) {// 新建玩家
		unLoginList.add(playerNameString);
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
	
	public static void playerQuit(Player player) {// 设置登陆状态
		unLoginList.remove(player.getName());
	}

	public static void playerQuit(String playerNameString) {// 设置登陆状态
		unLoginList.remove(playerNameString);
	}

	public static boolean isRegister(String playerName) {// 是否注册
		if (playerPasswordConfig.contains("playerName." + playerName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isRegister(Player player) {// 是否注册
		if (playerPasswordConfig.contains("playerName." + player.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean register(String playerName, String password) {// 注册
		if (isRegister(playerName)) {
			return false;
		} else {
			playerPasswordConfig.set("playerName." + playerName, encryptPassword(password));
			savePlayerPasswordConfig();
			return true;
		}
	}
	
	public static boolean register(Player player, String password) {// 注册
		String playerName=player.getName();
		if (isRegister(playerName)) {
			return false;
		} else {
			playerPasswordConfig.set("playerName." + playerName, encryptPassword(password));
			savePlayerPasswordConfig();
			return true;
		}
	}

	public static boolean checkPasswordReal(String playerName, String password) {// 检查密码
		if (!isRegister(playerName)) {
			return false;
		} else {
			String realPassword = playerPasswordConfig.getString("playerName." + playerName);
			if (encryptPassword(password).equals(realPassword)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean checkPasswordReal(Player player, String password) {// 检查密码
		String playerName=player.getName();
		if (!isRegister(playerName)) {
			return false;
		} else {
			String realPassword = playerPasswordConfig.getString("playerName." + playerName);
			if (encryptPassword(password).equals(realPassword)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean checkPasswordDtell(String password) {// 检查密码格式
		if (!(password.length() < 5 || password.length() > 20)) {
			return true;
		} else
			return false;
	}

	public static void savePlayerPasswordConfig() {
		try {
			playerPasswordConfig.save(playerPasswordFile);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			WICKHAMS_PLUGIN.getLogger().info("保存玩家密码失败");
		}
	}

//	https://cloud.tencent.com/developer/article/1128214
	private static String encryptPassword(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
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
