package com.wickham.minecraftPlugin.loginSystem;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
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
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class LoginLimitEvent implements Listener {
	public void noRegister(HumanEntity humanEntity) {// 未注册的信息
		humanEntity.sendMessage(ChatColor.RED + "你还没注册，输入/join 你的密码 来注册");
	}

	public void noLogin(HumanEntity humanEntity) {// 未登录的信息
		humanEntity.sendMessage(ChatColor.RED + "你还没登陆，输入/join 你的密码 来登陆");
	}

	@EventHandler
	public void listen(PlayerJoinEvent event) {// 玩家加入
		Player player=event.getPlayer();
		player.setGameMode(GameMode.SPECTATOR);
		LoginMain.newPlayer(player);
		if (LoginMain.isRegister(event.getPlayer().getName())==true)
			noLogin(event.getPlayer());
		else
			noRegister(event.getPlayer());
		return;
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
	public void stop(PlayerEditBookEvent event) {
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
	public void stop(PlayerEggThrowEvent event) {
		if (LoginMain.isLogin(event.getPlayer().getName())) {
			return;
		} else if (!LoginMain.isRegister(event.getPlayer().getName())) {
			noRegister(event.getPlayer());
			event.setHatching(false);
		} else {
			noLogin(event.getPlayer());
			event.setHatching(false);
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
			noRegister(event.getPlayer());
			event.setCancelled(true);
		} else {
			noLogin(event.getPlayer());
			event.setCancelled(true);
		}
	}

}
