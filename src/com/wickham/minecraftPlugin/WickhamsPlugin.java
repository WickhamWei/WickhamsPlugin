package com.wickham.minecraftPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wickham.minecraftPlugin.command.Tp;
import com.wickham.minecraftPlugin.command.TpAll;
import com.wickham.minecraftPlugin.API.teleport.WTeleportListener;
import com.wickham.minecraftPlugin.backSystem.BackCommand;
import com.wickham.minecraftPlugin.command.Hello;
import com.wickham.minecraftPlugin.command.Home;
import com.wickham.minecraftPlugin.command.SetHome;
import com.wickham.minecraftPlugin.command.Spawn;
import com.wickham.minecraftPlugin.event.WorldLoadingEvent;
import com.wickham.minecraftPlugin.event.WPlayerQuitEvent;
import com.wickham.minecraftPlugin.event.WPlayerInteractEvent;
import com.wickham.minecraftPlugin.event.WPlayerDeathEvent;
import com.wickham.minecraftPlugin.event.WPlayerJoinEvent;
import com.wickham.minecraftPlugin.event.WPlayerLevelChangeEvent;
import com.wickham.minecraftPlugin.loginSystem.LoginCommand;
import com.wickham.minecraftPlugin.loginSystem.LoginLimitEvent;
import com.wickham.minecraftPlugin.shapedRecipe.HugeRottenFlash;
import com.wickham.minecraftPlugin.tpASystem.TpACommand;
import com.wickham.minecraftPlugin.tpASystem.TpACommandYes;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class WickhamsPlugin extends JavaPlugin implements Listener {

	static WickhamsPlugin MAIN;// 建立主类静态变量
	FileConfiguration config = getConfig();// 加载默认config
	// 加载自己创建的config
	static FileConfiguration PLAYER_REGISTER_STATUS_CONFIG;// 建立自建config的静态FileConfiguration变量
	static File FILE_PLAYER_REGISTER_STATUS_CONFIG;// 建立自建config的静态File变量
	File playerRegisterStatusConfig = new File(this.getDataFolder(), "playerRegisterStatusConfig.yml");// 创建新File对象
	FileConfiguration playerRegisterStatus = YamlConfiguration.loadConfiguration(playerRegisterStatusConfig);// 创建新FileConfiguration对象

	@Override
	public void onEnable() {// 插件启动
		MAIN = this;// 赋值主类静态变量
		loadConfig();// 加载配置文件
		loadCommand();// 加载外挂指令
		loadListener();// 加载事件监听
		loadNewShapedRecipe();// 加载新合成表
	}

	@Override
	public void onDisable() {// 插件关闭
	}

	public void loadConfig() {// 读取配置文件
		// 自建配置文件的操作
		PLAYER_REGISTER_STATUS_CONFIG = playerRegisterStatus;// 静态变量赋值
		FILE_PLAYER_REGISTER_STATUS_CONFIG = playerRegisterStatusConfig;
		if (!playerRegisterStatusConfig.exists())
			this.saveResource("playerRegisterStatusConfig.yml", false);// look like same to next
		// 默认配置文件的操作
		this.saveDefaultConfig();// You can create a copy of config.yml from the jar into the plugin's data
									// folder by invoking JavaPlugin's saveDefaultConfig() method.
									// saveDefaultConfig() will not overwrite an existing file.
		// 内容操作
		playerRegisterStatus.addDefault("一般情况下禁止修改此文件，文件出错可能导致数据丢失", "此文件保存了玩家的密码");
		config.addDefault("=====温馨提示", "=====");
		config.addDefault("此文件编码格式为UTF-8", "请注意冒号后面有个空格，如遇到此文件出错，删除此文件即可重置。可以使用颜色代码。");
		config.addDefault("提示", "本插件自动保存的功能和Bukkit自带的自动保存功能重复，建议把bukkit.yml中autosave的值改为0以节省服务器资源");
		config.addDefault("不想要有公告/信息？冒号后不要留空，请这样", "");
		config.addDefault("=====登录系统", "=====");
		config.addDefault("登陆系统", false);
		config.addDefault("=====自动保存", "=====");
		config.addDefault("自动保存地图", true);
		config.addDefault("自动保存地图时间间隔，单位分钟", 10);
		config.addDefault("=====自动公告", "=====");
		config.addDefault("自动公告", false);
		config.addDefault("自动公告时间间隔，单位分钟", 1);
		config.addDefault("公告1", "欢迎来到Wickham的服务器");
		config.addDefault("公告2", "欢迎来到Wickham的服务器");
		config.addDefault("公告3", "欢迎来到Wickham的服务器");
		config.addDefault("=====其他信息", "=====");
		config.addDefault("玩家加入时给玩家的信息开关", false);
		config.addDefault("玩家加入时给玩家的信息", "欢迎来到Wickham的服务器");
		config.addDefault("玩家加入时的插件介绍", true);
		config.addDefault("=====玩家死亡", "=====");
		config.addDefault("死亡是否保留背包内的物品", false);
		config.addDefault("死亡后保留一半等级", false);
		config.addDefault("=====玩家升级", "=====");
		config.addDefault("三十级后每升级一级加血量上限", false);
		config.addDefault("最大血量上限，一颗心为两个血", 40);
		config.addDefault("=====地块保护", "=====");
		config.addDefault("保护耕地不被踩坏", true);
		config.addDefault("=====传送系统", "=====");
		config.addDefault("非OP传送等待时间（秒）", 3);
		config.addDefault("tpa请求等待时间（秒）", 20);

		// 后处理默认配置文件保存操作
		config.options().copyDefaults(true);// 如果没有看到上面的内容，拷贝一个进去
		saveConfig();// 保存

		// 后处理自建配置文件保存操作
		playerRegisterStatus.options().copyDefaults(true);// 如果没有看到上面的内容，拷贝一个进去
		try {
			playerRegisterStatus.save(playerRegisterStatusConfig);// 保存
		} catch (IOException event) {
			System.out.print("无法读取配置文件 playerRegisterStatusConfig ！服务器即将关闭！");
			Bukkit.shutdown();
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
	}

	public void loadListener() {// 读取事件
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new WPlayerJoinEvent(config), this);
		getServer().getPluginManager().registerEvents(new WPlayerQuitEvent(), this);
		getServer().getPluginManager().registerEvents(new WPlayerInteractEvent(config), this);
		getServer().getPluginManager().registerEvents(new WorldLoadingEvent(config, this), this);
		if (config.getBoolean("登陆系统"))
			getServer().getPluginManager().registerEvents(new LoginLimitEvent(), this);
		getServer().getPluginManager().registerEvents(new WTeleportListener(), this);
		getServer().getPluginManager().registerEvents(new WPlayerDeathEvent(config), this);
		getServer().getPluginManager().registerEvents(new WPlayerLevelChangeEvent(config), this);
	}

	public static WickhamsPlugin getMain() {// 外部调用主类方法
		return MAIN;
	}

	public static FileConfiguration getPlayerplayerRegisterStatusConfig() {// 外部调用配置文件方法
		return PLAYER_REGISTER_STATUS_CONFIG;
	}

	public static void savePlayerplayerRegisterStatusConfig() {// 外部保存配置文件方法
		try {
			PLAYER_REGISTER_STATUS_CONFIG.save(FILE_PLAYER_REGISTER_STATUS_CONFIG);
		} catch (IOException event) {
			System.out.print("无法保存配置文件 playerRegisterStatusConfig ！服务器即将关闭！");
			Bukkit.shutdown();
		}
	}

	public void loadNewShapedRecipe() {
		HugeRottenFlash.newHugeRottenFlash();
	}
}
