package wickhamsPlugin.loginSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import wickhamsPlugin.WickhamsPlugin;
import wickhamsPlugin.event.WPlayerLoginEvent;

public class LoginLimitListener implements Listener {
	public void noRegister(Player player) {// 未注册的信息
		player.sendTitle("", ChatColor.RED + "你还没注册，输入/join 你的密码 来注册", 5, 70, 5);
	}

	public void noLogin(Player player) {// 未登录的信息
		player.sendTitle("", ChatColor.RED + "你还没登陆，输入/join 你的密码 来登陆", 5, 70, 5);
	}

	@EventHandler
	public void listen(PlayerJoinEvent event) {// 玩家加入
		Player player = event.getPlayer();
		LoginMain.newPlayer(player);
		if (LoginMain.isInLoginTimesHashmap(player)) {
			if (LoginMain.getPlayerLoginTimes(player) >= 3) {
				player.kickPlayer("你输错了多次密码，请五分钟后再次尝试");
				return;
			}
		}
		if (!LoginMain.isKeepPlayerLogin(player)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.teleport(player.getWorld().getSpawnLocation());
			LoginTimeLimit.loginTimeLimit(player);
			LoginMain.addInLoginTimesHashMap(player);
			if (LoginMain.isRegister(event.getPlayer().getName()))
				noLogin(event.getPlayer());
			else
				noRegister(event.getPlayer());
			return;
		} else {
			WPlayerLoginEvent wPlayerLoginEvent = new WPlayerLoginEvent(player);
			Bukkit.getPluginManager().callEvent(wPlayerLoginEvent);
			if (!(wPlayerLoginEvent.isCancelled())) {
				LoginMain.playerLogin(player);
				player.sendMessage(ChatColor.GREEN + "已自动为你登录，欢迎回来");
				Bukkit.broadcastMessage(wPlayerLoginEvent.getJoinMsg());
				player.setGameMode(GameMode.SURVIVAL);
			}
			return;
		}
	}

	@EventHandler
	public void stop(PlayerCommandPreprocessEvent event) {// 不让未登录的玩家使用任何除join以外的指令
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;// 不管
		} else {
			String message = event.getMessage();
			String[] messageArray = message.split(" ");
			if (messageArray[0].equalsIgnoreCase("/join")) {// 检查使用的join命令是否合法，分割字符串
				return;
			} else {
				if (!LoginMain.isRegister(event.getPlayer().getName())) {
					noRegister(event.getPlayer());
					event.setCancelled(true);
				} else {
					noLogin(event.getPlayer());
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void stop(PlayerMoveEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(PlayerArmorStandManipulateEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(PlayerBedEnterEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(PlayerBedLeaveEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setSpawnLocation(false);
		} else {
			noLogin(event.getPlayer());
			event.setSpawnLocation(false);
		}
	}

	@EventHandler
	public void stop(PlayerBucketEmptyEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(AsyncPlayerChatEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(PlayerDropItemEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(PlayerInteractEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void stop(InventoryOpenEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(WickhamsPlugin.MAIN.getServer().getPlayer(event.getPlayer().getName()));
			event.setCancelled(true);
		} else {
			noLogin(WickhamsPlugin.MAIN.getServer().getPlayer(event.getPlayer().getName()));
			event.setCancelled(true);
		}
	}

}
