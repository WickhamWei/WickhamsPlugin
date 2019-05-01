package com.wickham.minecraftPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.wickham.minecraftPlugin.command.Tp;
import com.wickham.minecraftPlugin.command.TpAll;
import com.wickham.minecraftPlugin.command.gm;
import com.wickham.minecraftPlugin.API.teleport.WTeleportListener;
import com.wickham.minecraftPlugin.backSystem.BackCommand;
import com.wickham.minecraftPlugin.command.Hello;
import com.wickham.minecraftPlugin.command.Home;
import com.wickham.minecraftPlugin.command.SetHome;
import com.wickham.minecraftPlugin.command.Spawn;
import com.wickham.minecraftPlugin.event.WServerLoadEvent;
import com.wickham.minecraftPlugin.event.WPlayerQuitEvent;
import com.wickham.minecraftPlugin.event.WPlayerInteractEvent;
import com.wickham.minecraftPlugin.event.WPlayerDeathEvent;
import com.wickham.minecraftPlugin.event.WPlayerJoinEvent;
import com.wickham.minecraftPlugin.event.WPlayerLevelChangeEvent;
import com.wickham.minecraftPlugin.loginSystem.LoginCommand;
import com.wickham.minecraftPlugin.loginSystem.LoginLimitEvent;
import com.wickham.minecraftPlugin.loginSystem.LoginMain;
import com.wickham.minecraftPlugin.recipe.HugeRottenFlash;
import com.wickham.minecraftPlugin.tpASystem.TpACommand;
import com.wickham.minecraftPlugin.tpASystem.TpACommandYes;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class WickhamsPlugin extends JavaPlugin implements Listener {

	public static WickhamsPlugin MAIN;// 建立主类静态变量
	public final FileConfiguration mainConfiguration = getConfig();
	public final Server mainServer = getServer();

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
		if (mainConfiguration.getBoolean("登陆系统")) {
			LoginMain.createPlayerPasswordConfig();
			LoginMain.copyOldPasswordFile();
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
	}

	public void loadListener() {// 读取事件
		if (mainConfiguration.getBoolean("登陆系统")) {
			mainServer.getPluginManager().registerEvents(new LoginLimitEvent(), this);
		}
		mainServer.getPluginManager().registerEvents(this, this);
		mainServer.getPluginManager().registerEvents(new WPlayerJoinEvent(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new WPlayerQuitEvent(), this);
		mainServer.getPluginManager().registerEvents(new WPlayerInteractEvent(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new WServerLoadEvent(mainConfiguration, this), this);
		mainServer.getPluginManager().registerEvents(new WTeleportListener(), this);
		mainServer.getPluginManager().registerEvents(new WPlayerDeathEvent(mainConfiguration), this);
		mainServer.getPluginManager().registerEvents(new WPlayerLevelChangeEvent(mainConfiguration), this);
	}

	public void loadRecipe() {
		mainServer.getPluginManager().registerEvents(new HugeRottenFlash(), this);
	}
}
