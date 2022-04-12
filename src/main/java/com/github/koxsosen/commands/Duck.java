package com.github.koxsosen.commands;


import com.github.koxsosen.Constants;
import net.engio.mbassy.listener.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.kitteh.irc.client.library.event.client.ClientReceiveCommandEvent;
import org.kitteh.irc.client.library.feature.filter.CommandFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class Duck {

    private static final Logger logger = LogManager.getLogger(Duck.class);

    @CommandFilter("DUCK")
    @Handler
    public void onDuckCommand(ClientReceiveCommandEvent event) {
        if (event.getCommand().equals(Constants.PREFIX() + "duck")) {

            AtomicReference <String> imageUrl = new AtomicReference <>("**Ducky** - No ducks were found!");

            try {
                HttpURLConnection con = (HttpURLConnection) new URL("https://random-d.uk/api/v2/random").openConnection();
                con.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String s = br.readLine();

                if (s != null && s.length() > 0) {
                    JSONObject jsonObject = new JSONObject(s.trim());
                    imageUrl.set(jsonObject.getString("url"));
                }

            } catch (IOException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            event.getClient().sendMessage(event.getSource().toString(), imageUrl.toString());
            logger.info(event.getSource());
        }
    }
}
