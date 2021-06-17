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

public class CatCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(CatCommand.class);

    @Command(aliases = {Constants.PREFIX + "cat"}, async = true, description = "Get a random cat image from the web")
    public void onCommand(TextChannel channel, Message message) {

        String catimageUrl = "none";

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://api.thecatapi.com/v1/images/search").openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String s = br.readLine();

            if (s != null && s.length() > 0) {
                JSONArray jsonArray = new JSONArray(s.trim());
                catimageUrl = jsonArray.getJSONObject(0).getString("url");
            }

        } catch (IOException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        channel.sendMessage(catimageUrl);
        logger.info(message.getAuthor() + " requested this command.");
    }


}

