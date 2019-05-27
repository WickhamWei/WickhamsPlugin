package wickhamsPlugin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WickhamsPluginUpdateChecker {
	private static String urlString = "https://api.github.com/repos/WickhamWei/WickhamsPlugin/releases/latest";
	private static String charset=java.nio.charset.StandardCharsets.UTF_8.name();
	private static HttpURLConnection httpURLConnection;
	private static URL url;

	public static void UpdateChecker() {
		try {
			url=new URL(urlString);
			httpURLConnection=(HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(15000);
			httpURLConnection.setReadTimeout(15000);
			httpURLConnection.setRequestProperty("Accept-Charset", charset);
			httpURLConnection.connect();
			Map<String,List<String>> map =httpURLConnection.getHeaderFields();
			System.out.println("\n显示响应Header信息...\n");
            //遍历所有的响应头字段并输出
            //方式一、
            for (String key : map.keySet()) {  
                System.out.println(key + " : " + map.get(key));  
            }  
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			httpURLConnection.disconnect();
		}
	}
}