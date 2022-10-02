package dev.lupluv.simplegamemode.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

    private static final String USER_AGENT  = "LUPLUV";// Change this!
    private static final String REQUEST_URL = "https://api.spiget.org/v2/resources/86743/versions/latest";

    public static String getNewestVersion(){
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", USER_AGENT);// Set User-Agent

            // If you're not sure if the request will be successful,
            // you need to check the response code and use #getErrorStream if it returned an error code
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // This could be either a JsonArray or JsonObject
            JsonElement element = new JsonParser().parse(reader);
            if (element.isJsonArray()) {
                // Is JsonArray
            } else if (element.isJsonObject()) {
                // Is JsonObject
                JsonObject jsonObject = element.getAsJsonObject();
                String version = jsonObject.get("name").getAsString();
                System.out.println("Newest Version: " + version);
                return version;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public static void sendUpdateMessage(Player p){
        p.sendMessage(Strings.prefix + "§cYou are using an old version of this plugin.");
        p.sendMessage(Strings.prefix + "§cPlease update at https://www.spigotmc.org/resources/simple-gamemode-gm-1-8-1-19.86743/");
    }

}
