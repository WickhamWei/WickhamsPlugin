package wickhamsPlugin.RPG;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import wickhamsPlugin.WickhamsPlugin;

public abstract class Reflection {
	public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception ex) {
        	WickhamsPlugin.MAIN.getLogger().warning("Reflection.sendPacket error");
        }
    }

    public  Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server"
                    + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ex) {
        	WickhamsPlugin.MAIN.getLogger().warning("Reflection.getNMSClass error");
        }
        return null;
    }
}
