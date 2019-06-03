package wickhamsPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import com.google.gson.Gson;

public class WickhamsPluginUpdateChecker {
	private static String urlString = "https://api.github.com/repos/WickhamWei/WickhamsPlugin/releases/latest";
	private static HttpURLConnection httpURLConnection;
	private static URL url;
	private final static Plugin WICKHAMS_PLUGIN = WickhamsPlugin.MAIN;
	public static String PluginsNewestVersionString;
	public static String PluginstheNewestVersionPTimeString;
	public static String PluginsnowVersionString;

	public static void UpdateChecker() {
		try {
			url = new URL(urlString);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(15000);
			httpURLConnection.setReadTimeout(15000);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.connect();
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
			String allOriginJsonString = responseReader.readLine();
			Gson gson = new Gson();
			Github github = gson.fromJson(allOriginJsonString, Github.class);
			String nowPluginVersionString = new String("v" + WickhamsPlugin.MAIN.getDescription().getVersion());
			PluginsNewestVersionString=github.getLastVersion();
			PluginstheNewestVersionPTimeString=github.getLastVersionPublishedTime();
			PluginsnowVersionString=nowPluginVersionString;
			if (!nowPluginVersionString.equalsIgnoreCase(github.getLastVersion())) {
				Bukkit.getConsoleSender().sendMessage("[WickhamsPlugin] " + ChatColor.GREEN + "WickhamsPlugin 加载完成，版本 "
						+ ChatColor.YELLOW + "v" + WICKHAMS_PLUGIN.getDescription().getVersion());
				Bukkit.getConsoleSender()
						.sendMessage("[WickhamsPlugin] " + ChatColor.GREEN + "最新版本为 " + ChatColor.YELLOW
								+ github.getLastVersion() + ChatColor.GREEN + " 发布于 " + ChatColor.YELLOW
								+ github.getLastVersionPublishedTime() + ChatColor.GREEN + " 请及时更新");
			} else {
				Bukkit.getConsoleSender().sendMessage("[WickhamsPlugin] " + ChatColor.GREEN + "WickhamsPlugin 加载完成，版本 "
						+ ChatColor.YELLOW + "v" + WICKHAMS_PLUGIN.getDescription().getVersion());
				Bukkit.getConsoleSender().sendMessage("[WickhamsPlugin] " + ChatColor.GREEN + "你的版本已最新");
			}
		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			WICKHAMS_PLUGIN.getLogger().log(Level.WARNING, "检查 WickhamsPlugin 插件版本更新失败");
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			httpURLConnection.disconnect();
		}
	}
}

//{"url":"https://api.github.com/repos/WickhamWei/WickhamsPlugin/releases/17599287","assets_url":"https://api.github.com/repos/WickhamWei/WickhamsPlugin/releases/17599287/assets","upload_url":"https://uploads.github.com/repos/WickhamWei/WickhamsPlugin/releases/17599287/assets{?name,label}","html_url":"https://github.com/WickhamWei/WickhamsPlugin/releases/tag/v1.2.9.6","id":17599287,"node_id":"MDc6UmVsZWFzZTE3NTk5Mjg3","tag_name":"v1.2.9.6","target_commitish":"master","name":"更新1.2.9.6 (spigot 1.14.1)","draft":false,"author":{"login":"WickhamWei","id":31530701,"node_id":"MDQ6VXNlcjMxNTMwNzAx","avatar_url":"https://avatars3.githubusercontent.com/u/31530701?v=4","gravatar_id":"","url":"https://api.github.com/users/WickhamWei","html_url":"https://github.com/WickhamWei","followers_url":"https://api.github.com/users/WickhamWei/followers","following_url":"https://api.github.com/users/WickhamWei/following{/other_user}","gists_url":"https://api.github.com/users/WickhamWei/gists{/gist_id}","starred_url":"https://api.github.com/users/WickhamWei/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/WickhamWei/subscriptions","organizations_url":"https://api.github.com/users/WickhamWei/orgs","repos_url":"https://api.github.com/users/WickhamWei/repos","events_url":"https://api.github.com/users/WickhamWei/events{/privacy}","received_events_url":"https://api.github.com/users/WickhamWei/received_events","type":"User","site_admin":false},"prerelease":false,"created_at":"2019-05-27T05:41:33Z","published_at":"2019-05-27T05:48:40Z","assets":[{"url":"https://api.github.com/repos/WickhamWei/WickhamsPlugin/releases/assets/12882462","id":12882462,"node_id":"MDEyOlJlbGVhc2VBc3NldDEyODgyNDYy","name":"WickhamsPlugin.jar","label":null,"uploader":{"login":"WickhamWei","id":31530701,"node_id":"MDQ6VXNlcjMxNTMwNzAx","avatar_url":"https://avatars3.githubusercontent.com/u/31530701?v=4","gravatar_id":"","url":"https://api.github.com/users/WickhamWei","html_url":"https://github.com/WickhamWei","followers_url":"https://api.github.com/users/WickhamWei/followers","following_url":"https://api.github.com/users/WickhamWei/following{/other_user}","gists_url":"https://api.github.com/users/WickhamWei/gists{/gist_id}","starred_url":"https://api.github.com/users/WickhamWei/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/WickhamWei/subscriptions","organizations_url":"https://api.github.com/users/WickhamWei/orgs","repos_url":"https://api.github.com/users/WickhamWei/repos","events_url":"https://api.github.com/users/WickhamWei/events{/privacy}","received_events_url":"https://api.github.com/users/WickhamWei/received_events","type":"User","site_admin":false},"content_type":"application/octet-stream","state":"uploaded","size":193330,"download_count":0,"created_at":"2019-05-27T05:48:37Z","updated_at":"2019-05-27T05:48:40Z","browser_download_url":"https://github.com/WickhamWei/WickhamsPlugin/releases/download/v1.2.9.6/WickhamsPlugin.jar"}],"tarball_url":"https://api.github.com/repos/WickhamWei/WickhamsPlugin/tarball/v1.2.9.6","zipball_url":"https://api.github.com/repos/WickhamWei/WickhamsPlugin/zipball/v1.2.9.6","body":"1. 更新spigot-api\r\n2. 新增WShapeRecipe API，将ItemStark作为ShapedRecipe的RecipeSource\r\n3. 新增未登录的玩家就算是服务器后台操作也无法成为/gm的对象\r\n4. 新增武器等级限制系统，未达到相应的等级无法使用该武器\r\n5. 新增自定义僵尸掉落物\r\n6. 新增jj怪地形保护，修复jj怪爆炸\r\n7. 修复睡觉时不保存出生点\r\n8. 新增登录超时将踢出\r\n9. 更新尝试更好的实现WTeleport\r\n10. 新增仿DNF武器系统"}