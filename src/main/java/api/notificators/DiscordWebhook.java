package api.notificators;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class DiscordWebhook {
	private String webhookURL;
	
	public DiscordWebhook(){ 
		this.webhookURL = DiscordWebhook.getWebhookURL();
	}
	public void send(String content) {
		System.out.println(webhookURL);
		JSONObject json = new JSONObject();
		json.put("content", content);    
		System.out.println(content);
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
		    HttpPost request = new HttpPost(webhookURL);
		    StringEntity params = new StringEntity(json.toString(), "UTF-8");
		    System.out.println(json.toString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    httpClient.execute(request);
		// handle response here...
		} catch (Exception ex) {
		    // handle exception here
			ex.printStackTrace();
		} finally {
		    try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String getWebhookURL() {
		Properties prop = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("config.properties");
		    prop.load(fileInputStream);
		    fileInputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return prop.getProperty("discord_webhook_url");
		
	}
	public static void main(String[] args) {
		
	}
}
