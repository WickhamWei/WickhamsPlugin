package wickhamsPlugin.tpASystem;

import java.util.HashMap;
import org.bukkit.entity.Player;

import wickhamsPlugin.WickhamsPlugin;

public abstract class TpAMain{
	public static HashMap<String, String> requestList = new HashMap<>();//存储传送事件
	
	static final int REQUEST_WAITING_TIME=WickhamsPlugin.MAIN.getConfig().getInt("tpa请求等待时间（秒）");

	public static void newRequest(Player player,Player targe) {
		requestList.put(player.getName(),targe.getName());
	} //新建等待传送事件
	
	public static boolean isRequestFirst(Player player) {
		return requestList.containsKey(player.getName());
	}
	
	public static boolean isRequestSecond(Player player) {
		return requestList.containsValue(player.getName());
	}
	
	public static void cancelRequest(Player player) {
		requestList.remove(player.getName());
	}
	
	public abstract boolean requestTeleport(Player player,Player targe);//执行传送
}
