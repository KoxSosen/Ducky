package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import net.engio.mbassy.listener.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.kitteh.irc.client.library.event.client.ClientReceiveCommandEvent;
import org.kitteh.irc.client.library.feature.filter.CommandFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Dog {

    private static final Logger logger = LogManager.getLogger(Duck.class);

    @CommandFilter("DUCK")
    @Handler
    public void onDogkCommand(ClientReceiveCommandEvent event) {
        if (event.getCommand().equals(Constants.PREFIX() + "dog")) {

            String dogimageUrl = "**Ducky** - No dogs were found!";

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
            event.getClient().sendMessage(event.getSource().toString(), dogimageUrl);
            logger.info(event.getSource());
        }
    }
}
