package wickhamsPlugin.loginSystem;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoginTimesLimit {
	protected static void newLoginTimesLimit(Player player) {
		BukkitRunnable loginTimesLimitBukkitRunnable = new BukkitRunnable() {
			Player targePlayer = player;

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				LoginMain.removePlayerInLoginTimesHashMap(targePlayer);
			}
		};
		loginTimesLimitBukkitRunnable.runTaskLater(LoginMain.WICKHAMS_PLUGIN, 20 * 60 * 5);
	}
}
