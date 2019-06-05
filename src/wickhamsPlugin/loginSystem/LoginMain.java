package wickhamsPlugin.loginSystem;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import wickhamsPlugin.WickhamsPlugin;

public final class LoginMain {
	protected static final Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	private static File playerPasswordFile;
	private static FileConfiguration playerPasswordConfig;
	private static File playerRegisterIPFile;
	private static FileConfiguration playerRegisterIPConfig;
	public static final boolean PLAYER_REGISTER_IP_LIMIT=WICKHAMS_PLUGIN.getConfig().getBoolean("每个IP只能注册一个账号");
	private static HashMap<String, Integer> keepPlayerLoginHashMap = new HashMap<String, Integer>();
	private static final int loginKeepTime = WICKHAMS_PLUGIN.getConfig().getInt("登录保持时间");
	private static HashMap<String, Integer> loginTimesHashMap = new HashMap<String, Integer>();
	protected static boolean joinMsgBoolean = WickhamsPlugin.MAIN.getConfig().getBoolean("玩家加入时给玩家的信息开关");
	protected static String joinMsgString = WickhamsPlugin.MAIN.getConfig().getString("玩家加入时给玩家的信息");

	public static void createPlayerPasswordConfig() {
		playerPasswordFile = new File(WICKHAMS_PLUGIN.getDataFolder(), "playerPassword.yml");
		if (!playerPasswordFile.exists()) {
			playerPasswordFile.getParentFile().mkdirs();
			WICKHAMS_PLUGIN.saveResource("playerPassword.yml", false);
		}
		playerPasswordConfig = new YamlConfiguration();
		try {
			playerPasswordConfig.load(playerPasswordFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getPlayerPasswordConfig() {
		return playerPasswordConfig;
	}

	public static File getPlayerPasswordFile() {
		return playerPasswordFile;
	}

	public static void copyOldPasswordFile() {
		File playerRegisterStatusConfigFile = new File(WickhamsPlugin.MAIN.getDataFolder(),
				"playerRegisterStatusConfig.yml");
		if (!playerRegisterStatusConfigFile.exists()) {
			return;
		} else {
			WICKHAMS_PLUGIN.getLogger().info("找到旧的密码文件，正在自动加密");
			FileConfiguration playerOldPasswordConfiguration = YamlConfiguration
					.loadConfiguration(playerRegisterStatusConfigFile);
			Set<String> playerList = playerOldPasswordConfiguration.getConfigurationSection("playerName")
					.getKeys(false);
			if (playerList.isEmpty()) {
				WICKHAMS_PLUGIN.getLogger().info("文件为空，已忽略");
				return;
			}
			for (String playerName : playerList) {
				String password = playerOldPasswordConfiguration.getString("playerName." + playerName);
				if (password != null) {
					if (playerPasswordConfig.contains("playerName." + playerName)) {
						continue;
					} else {
						playerPasswordConfig.set("playerName." + playerName, encryptPassword(password));
						WICKHAMS_PLUGIN.getLogger().info("已经加密玩家 " + playerName + " 的密码");
					}
				}
			}
			savePlayerPasswordConfig();
			playerRegisterStatusConfigFile.delete();
			WICKHAMS_PLUGIN.getLogger().info("已经删除旧的密码文件");
			return;
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
		String playerName = player.getName();
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
		String playerName = player.getName();
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
			WICKHAMS_PLUGIN.getLogger().warning("玩家密码的文件保存失败");
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

	public static void recordPlayerIPAddressInConfig(Player player) {
		String IPString = getPlayerIPAddress(player);
		playerPasswordConfig.set("playerIP.playerName." + player.getName(), encryptPassword(IPString));
		savePlayerPasswordConfig();
	}

	public static boolean hasPlayerIPAddressInConfig(Player player) {
		return playerPasswordConfig.contains("playerIP.playerName." + player.getName());
	}

	private static String getPlayerIPAddress(Player player) {
		return player.getAddress().getAddress().getHostAddress();
	}

	public static boolean isKeepPlayerLogin(Player player) {
		if (!keepPlayerLoginHashMap.containsKey(player.getName())) {
			return false;
		}
		String lastIPString = playerPasswordConfig.getString("playerIP.playerName." + player.getName());
		if (encryptPassword(getPlayerIPAddress(player)).equals(lastIPString)) {
			Bukkit.getScheduler().cancelTask(keepPlayerLoginHashMap.get(player.getName()));
			keepPlayerLoginHashMap.remove(player.getName());
			return true;
		} else {
			Bukkit.getScheduler().cancelTask(keepPlayerLoginHashMap.get(player.getName()));
			keepPlayerLoginHashMap.remove(player.getName());
			return false;
		}
	}

	public static void countDownWhenPlayerLeft(Player player) {
		if (isLogin(player)) {
			BukkitRunnable countDownBukkitRunnable = new BukkitRunnable() {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					keepPlayerLoginHashMap.remove(player.getName());
				}

			};
			countDownBukkitRunnable.runTaskLater(WICKHAMS_PLUGIN, 20 * 60 * loginKeepTime);
			keepPlayerLoginHashMap.put(player.getName(), countDownBukkitRunnable.getTaskId());
		}
	}

	public static void savePlayerLastLocation(Player player, Location location) {
		if (isLogin(player)) {
			String world = location.getWorld().getName();
			double X = location.getX();
			double Y = location.getY();
			double Z = location.getZ();
			playerPasswordConfig.set("playerLastLocation.playerName." + player.getName() + ".world", world);
			playerPasswordConfig.set("playerLastLocation.playerName." + player.getName() + ".X", X);
			playerPasswordConfig.set("playerLastLocation.playerName." + player.getName() + ".Y", Y);
			playerPasswordConfig.set("playerLastLocation.playerName." + player.getName() + ".Z", Z);
			savePlayerPasswordConfig();
		}
	}

	public static boolean teleportPlayerAfterLogin(Player player) {
		if (isLogin(player)) {
			if (playerPasswordConfig.contains("playerLastLocation.playerName." + player.getName() + ".world")) {
				World world = Bukkit.getWorld(
						playerPasswordConfig.getString("playerLastLocation.playerName." + player.getName() + ".world"));
				double X = playerPasswordConfig.getDouble("playerLastLocation.playerName." + player.getName() + ".X");
				double Y = playerPasswordConfig.getDouble("playerLastLocation.playerName." + player.getName() + ".Y");
				double Z = playerPasswordConfig.getDouble("playerLastLocation.playerName." + player.getName() + ".Z");
				return player.teleport(new Location(world, X, Y, Z));
			}
		}
		return false;
	}

	public static void addInLoginTimesHashMap(Player player) {
		String playerNameString = player.getName();
		loginTimesHashMap.put(playerNameString, 0);
	}

	public static void removePlayerInLoginTimesHashMap(Player player) {
		String playerNameString = player.getName();
		loginTimesHashMap.remove(playerNameString);
	}

	public static int getPlayerLoginTimes(Player player) {
		String playerNameString = player.getName();
		return loginTimesHashMap.get(playerNameString);
	}

	public static void addPlayerLoginTimes(Player player) {
		String playerNameString = player.getName();
		loginTimesHashMap.put(playerNameString, loginTimesHashMap.get(playerNameString) + 1);
	}

	public static boolean isInLoginTimesHashmap(Player player) {
		String playerNameString = player.getName();
		return loginTimesHashMap.containsKey(playerNameString);
	}
	
	public static void createPlayerRegisterIPConfig() {
		playerRegisterIPFile = new File(WICKHAMS_PLUGIN.getDataFolder(), "playerRegisterIP.yml");
		if (!playerRegisterIPFile.exists()) {
			playerRegisterIPFile.getParentFile().mkdirs();
			WICKHAMS_PLUGIN.saveResource("playerRegisterIP.yml", false);
		}
		playerRegisterIPConfig = new YamlConfiguration();
		try {
			playerRegisterIPConfig.load(playerRegisterIPFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
			WICKHAMS_PLUGIN.getLogger().log(Level.WARNING, playerRegisterIPFile.getName()+" 读取失败");
		}
	}

	public static FileConfiguration getPlayerRegisterIPConfig() {
		return playerRegisterIPConfig;
	}

	public static File getPlayerRegisterIPFile() {
		return playerRegisterIPFile;
	}
	
	public static void savePlayerRegisterIPConfig() {
		try {
			playerRegisterIPConfig.save(playerRegisterIPFile);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			WICKHAMS_PLUGIN.getLogger().warning(playerRegisterIPFile.getName()+ "保存失败");
		}
	}
	
	public static boolean hasRegisterIP(Player player) {
		String playerNameString = player.getName();
		return playerRegisterIPConfig.contains("playerName."+playerNameString);
	}
	
	public static void setRegisterIP(Player player) {
		String playerNameString = player.getName();
		playerRegisterIPConfig.set("playerName."+playerNameString, encryptPassword(getPlayerIPAddress(player)));
	}
	
	public static boolean isIPHasBeenUsed(Player player) {
		String playerIPString=encryptPassword(getPlayerIPAddress(player));
		Set<String> playerList = playerRegisterIPConfig.getConfigurationSection("playerName")
				.getKeys(false);
		for(String playerNameInList:playerList) {
			if(playerRegisterIPConfig.getString("playerName."+playerNameInList).equals(playerIPString)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean loginSystemIsEnable() {
		return WICKHAMS_PLUGIN.getConfig().getBoolean("登录系统");
	}
}
