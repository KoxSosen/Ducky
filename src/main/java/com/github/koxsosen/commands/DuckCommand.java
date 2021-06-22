package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class DuckCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(DuckCommand.class);

    @Command(aliases = {Constants.PREFIX + "duck"}, async = true, description = "Send a random duck image")
    public void onCommand(TextChannel channel, Message message) {

        AtomicReference <String> imageUrl = new AtomicReference <>("none");

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
        channel.sendMessage(imageUrl.get());
        logger.info(message.getAuthor());
    }
}
