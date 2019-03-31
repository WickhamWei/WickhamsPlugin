package com.wickham.minecraftPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wickham.minecraftPlugin.command.Tp;
import com.wickham.minecraftPlugin.command.TpAll;
import com.wickham.minecraftPlugin.backSystem.BackCommand;
import com.wickham.minecraftPlugin.command.Hello;
import com.wickham.minecraftPlugin.event.TpEvent;
import com.wickham.minecraftPlugin.event.WorldLoadingEvent;
import com.wickham.minecraftPlugin.event.LoginEvent;
import com.wickham.minecraftPlugin.event.QuitEvent;
import com.wickham.minecraftPlugin.event.SteppingOnEvent;
import com.wickham.minecraftPlugin.loginSystem.LoginCommand;
import com.wickham.minecraftPlugin.loginSystem.LoginLimitEvent;
import com.wickham.minecraftPlugin.loginSystem.LoginMain;
import com.wickham.minecraftPlugin.shapedRecipe.HugeRottenFlash;
import com.wickham.minecraftPlugin.spawnSystem.SpawnCommand;
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
//		loadNewShapedRecipe();//加载新合成表
		getLogger().info("Wickham插件成功启动");
	}

	@Override
	public void onDisable() {// 插件关闭
		if (config.getBoolean("登陆系统"))
			LoginMain.cleanPlayerList();// 清除表（没卵用）

		getLogger().info("Wickham插件已关闭");
	}

	public void loadConfig() {// 读取配置文件
		getLogger().info("正在读取配置文件 config");
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

		config.addDefault("此文件编码格式为UTF-8", "请注意冒号后面有个空格，如遇到此文件出错，删除此文件即可重置。可以使用颜色代码。");
		config.addDefault("提示", "本插件自动保存的功能和Bukkit自带的自动保存功能重复，建议把bukkit.yml中autosave的值改为0以节省服务器资源");
		config.addDefault("不想要有公告/信息？冒号后不要留空，请这样", "");
		config.addDefault("玩家加入时给玩家的信息开关", false);
//		getLogger().info("---玩家加入时给玩家的信息开关" + " " + config.getBoolean("玩家加入时给玩家的信息开关"));
		config.addDefault("玩家加入时给玩家的信息", "欢迎来到Wickham的服务器");
		config.addDefault("玩家加入时的插件介绍", true);
		config.addDefault("登陆系统", false);
//		getLogger().info("---登陆系统" + " " + config.getBoolean("登陆系统"));
		config.addDefault("自动保存地图", true);
//		getLogger().info("---自动保存地图" + " " + config.getBoolean("自动保存地图"));
		config.addDefault("自动保存地图时间间隔，单位分钟", 10);
//		getLogger().info("---自动保存地图时间间隔（分钟）" + " " + config.getInt("自动保存地图时间间隔，单位分钟"));
		config.addDefault("自动公告", false);
//		getLogger().info("---自动公告" + " " + config.getBoolean("自动公告"));
		config.addDefault("自动公告时间间隔，单位分钟", 1);
//		getLogger().info("---自动公告时间间隔（分钟）" + " " + config.getInt("自动公告时间间隔，单位分钟"));
		config.addDefault("公告1", "欢迎来到Wickham的服务器");
		config.addDefault("公告2", "欢迎来到Wickham的服务器");
		config.addDefault("公告3", "欢迎来到Wickham的服务器");
		config.addDefault("保护耕地不被踩坏", true);
//		getLogger().info("---保护耕地不被踩坏" + " " + config.getBoolean("保护耕地不被踩坏"));
		config.addDefault("死亡是否保留背包内的物品", false);
//		getLogger().info("---死亡是否保留背包内的物品" + " " + config.getBoolean("死亡是否保留背包内的物品"));
		config.addDefault("怪物能否捡起或更换物品", true);
//		getLogger().info("---怪物能否捡起或更换物品" + " " + config.getBoolean("怪物能否捡起或更换物品"));// Whether creepers, zombies,
																					// endermen,
																					// ghasts, withers, ender dragons,
																					// rabbits, sheep, villagers, and
																					// snow golems should be able to
																					// change blocks and whether mobs
																					// can pick up items
		config.addDefault("非OP传送等待时间（秒）", 3);
//		getLogger().info("---非OP传送等待时间（秒）" + " " + config.getInt("非OP传送等待时间（秒）"));
		config.addDefault("tpa请求等待时间（秒）", 20);
//		getLogger().info("---tpa请求等待时间（秒）" + " " + config.getInt("tpa请求等待时间（秒）"));
		
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
		getLogger().info("读取配置文件 config 完成");
	}

	public void loadCommand() {// 读取命令
		getLogger().info("正在读取命令");
		// 调用外部指令
		this.getCommand("你好").setExecutor(new Hello());
		this.getCommand("tp").setExecutor(new Tp());
		this.getCommand("spawn").setExecutor(new SpawnCommand());
		this.getCommand("tpall").setExecutor(new TpAll());
		this.getCommand("join").setExecutor(new LoginCommand());
		this.getCommand("back").setExecutor(new BackCommand());
		this.getCommand("tpa").setExecutor(new TpACommand());
		this.getCommand("tpayes").setExecutor(new TpACommandYes());// 这里不用判断 因为plugin必须注册指令
		getLogger().info("读取命令完成");
	}

	public void loadListener() {// 读取事件
		getLogger().info("正在读取事件");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new LoginEvent(), this);
		getServer().getPluginManager().registerEvents(new QuitEvent(), this);
		getServer().getPluginManager().registerEvents(new SteppingOnEvent(config), this);
		getServer().getPluginManager().registerEvents(new WorldLoadingEvent(config, this), this);
		if (config.getBoolean("登陆系统"))
			getServer().getPluginManager().registerEvents(new LoginLimitEvent(), this);
		getServer().getPluginManager().registerEvents(new TpEvent(), this);
		getLogger().info("读取事件完成");
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
		getLogger().info("正在读取合成表");
		HugeRottenFlash.newHugeRottenFlash();
        getLogger().info("读取合成表完成");
	}
}
