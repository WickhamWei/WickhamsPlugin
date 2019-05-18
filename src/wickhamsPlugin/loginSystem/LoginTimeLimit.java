package wickhamsPlugin.loginSystem;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoginTimeLimit {
	protected final static int LOGIN_TIME_LIMIT_SECOND = LoginMain.WICKHAMS_PLUGIN.getConfig().getInt("登录限制时间");

	protected static void loginTimeLimit(Player player) {
		BukkitRunnable countDownBukkitRunnable = new BukkitRunnable() {
			Player mainplayer = player;
			int time=0;

			@Override
			public void run() {
				if(LoginMain.isLogin(mainplayer)) {
					cancel();
				}else {
					time++;
					if(time>=LOGIN_TIME_LIMIT_SECOND) {
						mainplayer.kickPlayer("您长时间未登录，已被踢出服务器");
						cancel();
					}else {
						return;
					}
				}
			}
		};
		countDownBukkitRunnable.runTaskTimer(LoginMain.WICKHAMS_PLUGIN, 0, 20);
	}
}
