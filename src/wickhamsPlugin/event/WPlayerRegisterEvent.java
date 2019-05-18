package wickhamsPlugin.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WPlayerRegisterEvent extends Event {

	private final Player player;
	private boolean isCancelled;
	private String joinMsgString;
	private String registerSuccessMsgString;

	public WPlayerRegisterEvent(Player player) {
		this.player = player;
		this.isCancelled = false;
		this.joinMsgString = ChatColor.GREEN + player.getName() + " 加入了游戏";
		this.registerSuccessMsgString = ChatColor.GREEN + "注册成功，已自动登陆";
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isCancelled() {
		return this.isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getJoinMsg() {
		return joinMsgString;
	}
	
	public void setJoinMsg(String joinMsgString) {
		this.joinMsgString=joinMsgString;
	}
	
	public String getRegisterSuccessMsg() {
		return registerSuccessMsgString;
	}
	
	public void setRegisterSuccessMsg(String loginSuccessMsgString) {
		this.registerSuccessMsgString=loginSuccessMsgString;
	}
	
	// This seems like all you need to do at a first glance, but after running your
	// program you'll get error messages from Spigot. This is because, although not
	// heavily documented, you need to incorporate the following methods into your
	// Event class

	private static final HandlerList HANDLERS = new HandlerList();

	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	// 以上为默认必须操作

}
