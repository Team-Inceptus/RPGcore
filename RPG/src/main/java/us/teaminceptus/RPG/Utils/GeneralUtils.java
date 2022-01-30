package us.teaminceptus.RPG.Utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import com.google.gson.Gson;

import us.teaminceptus.RPG.Rpg;

public class GeneralUtils {
	private static final String ChatColor = null;

	public Rpg plugin;
	
	public static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();


	
	
	
	
	
	
	
	
	

    
    public static String sendGETRequest(String url) {
    	try {
	    	HttpRequest request = HttpRequest.newBuilder()
	    			.GET()
	    			.uri(URI.create(url))
	    			.setHeader("User-Agent", "Java 11 HttpClient Bot")
	    			.build();
	    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	    	
	    	if (response.statusCode() == 200) {
	    		return response.body();
	    	}
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    public static int sendGETRequestStatusCode(String url) {
    	try {
	    	HttpRequest request = HttpRequest.newBuilder()
	    			.GET()
	    			.uri(URI.create(url))
	    			.setHeader("User-Agent", "Java 11 HttpClient Bot")
	    			.build();
	    	HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	    	
	    	return response.statusCode();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return 404;
    }
    
    
    
    public static UUID untrimUUID(String oldUUID) {
    	String p1 = oldUUID.substring(0, 8);
    	String p2 = oldUUID.substring(8, 12);
    	String p3 = oldUUID.substring(12, 16);
    	String p4 = oldUUID.substring(16, 20);
    	String p5 = oldUUID.substring(20, 32);
    	
    	String newUUID = p1 + "-" + p2 + "-" + p3 + "-" + p4 + "-" + p5;
    	
    	return UUID.fromString(newUUID);
    }
}
