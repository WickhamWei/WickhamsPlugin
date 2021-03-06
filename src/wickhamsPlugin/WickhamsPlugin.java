package wickhamsPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import wickhamsPlugin.API.shapedRecipe.WShapedRecipeListener;
import wickhamsPlugin.API.teleport.WTeleportCancelListener;
import wickhamsPlugin.RPG.WRPGEntitySpawnEventListener;
import wickhamsPlugin.RPG.WRPGPlayerMoveEventListener;
import wickhamsPlugin.backSystem.BackCommand;
import wickhamsPlugin.command.Hello;
import wickhamsPlugin.command.Home;
import wickhamsPlugin.command.SetHome;
import wickhamsPlugin.command.SetLevel;
import wickhamsPlugin.command.SetSpawn;
import wickhamsPlugin.command.Spawn;
import wickhamsPlugin.command.Tp;
import wickhamsPlugin.command.TpAll;
import wickhamsPlugin.command.gm;
import wickhamsPlugin.eventListener.EntityExplodeEventListener;
import wickhamsPlugin.eventListener.PlayerBedEnterEventListener;
import wickhamsPlugin.eventListener.PlayerDeathEventListener;
import wickhamsPlugin.eventListener.PlayerInteractEventListener;
import wickhamsPlugin.eventListener.PlayerJoinEventListener;
import wickhamsPlugin.eventListener.PlayerLevelChangeEventListener;
import wickhamsPlugin.eventListener.PlayerQuitEventListener;
import wickhamsPlugin.eventListener.ServerLoadEventListener;
import wickhamsPlugin.eventListener.WPlayerLoginEventListener;
import wickhamsPlugin.eventListener.WPlayerRegisterEventListener;
import wickhamsPlugin.loginSystem.LoginCommand;
import wickhamsPlugin.loginSystem.LoginLimitListener;
import wickhamsPlugin.loginSystem.LoginMain;
import wickhamsPlugin.recipe.HugeRottenFlash;
import wickhamsPlugin.recipe.RPGWeapons.WDNFEntityDropItemListener;
import wickhamsPlugin.recipe.RPGWeapons.WDNFLoadingAllRecipeListener;
import wickhamsPlugin.recipe.RPGWeapons.WDNFWeaponLevelLimitListener;
import wickhamsPlugin.tpASystem.TpACommand;
import wickhamsPlugin.tpASystem.TpACommandYes;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class WickhamsPlugin extends JavaPlugin implements Listener {

	public static WickhamsPlugin MAIN;// 主类静态变量
	public final FileConfiguration mainConfiguration = getConfig();// 终态配置文件
	public final Server mainServer = getServer();// 终态服务器
	public boolean loginSystemBoolean = mainConfiguration.getBoolean("登陆系统");
	public boolean RPGBoolean = mainConfiguration.getBoolean("RPG模式");
	public boolean jjProtectBoolean = mainConfiguration.getBoolean("阻止jj怪爆炸破坏地形");

	@Override
	public void onEnable() {// 插件启动
		MAIN = this;// 赋值主类静态变量
		loadConfig();// 加载配置文件
		loadCommand();// 加载外挂指令
		loadListener();// 加载事件监听
		loadRecipe();// 加载新合成表
	}

	@Override
	public void onDisable() {// 插件关闭
	}

	public void loadConfig() {// 读取配置文件
		this.saveDefaultConfig();// You can create a copy of config.yml from the jar into the plugin's data
									// folder by invoking JavaPlugin's saveDefaultConfig() method.
									// saveDefaultConfig() will not overwrite an existing file.

		if (loginSystemBoolean) {
			if(LoginMain.PLAYER_REGISTER_IP_LIMIT) {
				LoginMain.createPlayerRegisterIPConfig();
			}
			LoginMain.createPlayerPasswordConfig();
			LoginMain.copyOldPasswordFile();// ⑨的登录系统配置文件迁移、加密、删除
		}
	}

	public void loadCommand() {// 读取命令
		this.getCommand("你好").setExecutor(new Hello());
		this.getCommand("tp").setExecutor(new Tp());
		this.getCommand("spawn").setExecutor(new Spawn());
		this.getCommand("tpall").setExecutor(new TpAll());
		this.getCommand("join").setExecutor(new LoginCommand());
		this.getCommand("back").setExecutor(new BackCommand());
		this.getCommand("home").setExecutor(new Home());
		this.getCommand("tpa").setExecutor(new TpACommand());
		this.getCommand("tpayes").setExecutor(new TpACommandYes());
		this.getCommand("sethome").setExecutor(new SetHome());
		this.getCommand("gm").setExecutor(new gm());
		this.getCommand("setlevel").setExecutor(new SetLevel());
		this.getCommand("setspawn").setExecutor(new SetSpawn());
	}

	public void loadListener() {// 读取事件
		if (loginSystemBoolean) {
			mainServer.getPluginManager().registerEvents(new LoginLimitListener(), this);
		}
		if (jjProtectBoolean) {
			mainServer.getPluginManager().registerEvents(new EntityExplodeEventListener(), this);
		}
		mainServer.getPluginManager().registerEvents(this, this);
		mainServer.getPluginManager().registerEvents(new PlayerBedEnterEventListener(), this);
		mainServer.getPluginManager().registerEvents(new PlayerJoinEventListener(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new PlayerQuitEventListener(), this);
		mainServer.getPluginManager().registerEvents(new PlayerInteractEventListener(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new ServerLoadEventListener(), this);
		mainServer.getPluginManager().registerEvents(new WTeleportCancelListener(), this);
		mainServer.getPluginManager().registerEvents(new PlayerDeathEventListener(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new PlayerLevelChangeEventListener(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new WPlayerLoginEventListener(), this);
		mainServer.getPluginManager().registerEvents(new WPlayerRegisterEventListener(), this);
		mainServer.getPluginManager().registerEvents(new WShapedRecipeListener(), this);
		if (RPGBoolean) {
			mainServer.getPluginManager().registerEvents(new WDNFLoadingAllRecipeListener(), this);
			mainServer.getPluginManager().registerEvents(new WDNFWeaponLevelLimitListener(), this);
			mainServer.getPluginManager().registerEvents(new WDNFEntityDropItemListener(), this);
			mainServer.getPluginManager().registerEvents(new WRPGEntitySpawnEventListener(), this);
			mainServer.getPluginManager().registerEvents(new WRPGPlayerMoveEventListener(), this);
		}
	}

	public void loadRecipe() {// 读取新的合成表
		if (mainConfiguration.getBoolean("巨大腐肉")) {
			mainServer.getPluginManager().registerEvents(new HugeRottenFlash(), this);
		}
	}

	public static FileConfiguration createConfig(String fileNameString) {
		File file = new File(MAIN.getDataFolder(), fileNameString + ".yml");
		if (!file.exists()) {
			MAIN.getLogger().log(Level.WARNING, fileNameString + " 文件不存在");
			file.getParentFile().mkdirs();
			MAIN.saveResource(fileNameString + ".yml", false);
		}
		YamlConfiguration config = new YamlConfiguration();
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
			MAIN.getLogger().log(Level.WARNING, fileNameString + " 配置文件创建失败");
		}
		return config;
	}

	public static void saveConfig(FileConfiguration configuration, String fileNameString) {
		try {
			configuration.save(new File(MAIN.getDataFolder(), fileNameString + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
			MAIN.getLogger().log(Level.WARNING, fileNameString + " 配置文件保存失败");
		}
	}
	
	public static void test() {
		WickhamsPlugin.MAIN.getServer().getLogger().warning("test");
	}
}
