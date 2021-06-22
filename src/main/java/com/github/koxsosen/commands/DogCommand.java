package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DogCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(DogCommand.class);

    @Command(aliases = {Constants.PREFIX + "dog"}, async = true, description = "Get a random dog image from the web")
    public void onCommand(TextChannel channel, Message message) {

        String dogimageUrl = "none";

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://api.thedogapi.com/v1/images/search").openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String s = br.readLine();

            if (s != null && s.length() > 0) {
                JSONArray jsonArray = new JSONArray(s.trim());
                dogimageUrl = jsonArray.getJSONObject(0).getString("url");
            }

        } catch (IOException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        channel.sendMessage(dogimageUrl);
        logger.info(message.getAuthor());
    }
}
