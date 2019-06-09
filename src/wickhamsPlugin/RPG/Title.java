package wickhamsPlugin.RPG;

import java.lang.reflect.Constructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import wickhamsPlugin.WickhamsPlugin;

//https://www.spigotmc.org/threads/send-titles-to-players-using-spigot-1-8-1-11-2.48819/

public class Title extends Reflection {
	public void send(Player player, String title, ChatColor titleColor, String subtitle, ChatColor subtitleColor,
			int fadeInTime, int showTime, int fadeOutTime) {
		try {
			Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
					.invoke(null, "{\"text\": \"" + title + "\",color:" + titleColor.name().toLowerCase() + "}");
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
					getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
					int.class, int.class, int.class);
			Object packet = titleConstructor.newInstance(
					getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
					fadeInTime, showTime, fadeOutTime);

			Object chatsTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
					.invoke(null, "{\"text\": \"" + subtitle + "\",color:" + subtitleColor.name().toLowerCase() + "}");
			Constructor<?> stitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
					getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"),
					int.class, int.class, int.class);
			Object spacket = stitleConstructor.newInstance(
					getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null),
					chatsTitle, fadeInTime, showTime, fadeOutTime);

			sendPacket(player, packet);
			sendPacket(player, spacket);
		} catch (Exception ex) {
			WickhamsPlugin.MAIN.getLogger().warning("Title class error");
		}
	}
}
