package com.github.koxsosen;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.moandjiezana.toml.Toml;

import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String CONFIG_TOML= "config.toml";

    public static void main(String[] args) {
        Main main;
        main = new Main();
        InputStream stream = main.getClass().getClassLoader().getResourceAsStream(CONFIG_TOML);

        Toml toml = new Toml().read(stream);
        String token = toml.getString("token");
        logger.info("Succesfully read the bots token which is" + token);

        FallbackLoggerConfiguration.setDebug(true);

        // Login using the disocrd api
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();
        api.setReconnectDelay(attempt -> attempt * 2);

        // Set the status of the bot

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });

        // Print the invite url of your bot
        logger.info("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}