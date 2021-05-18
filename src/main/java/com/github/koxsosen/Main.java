package com.github.koxsosen;

import com.github.koxsosen.commands.HelpCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.moandjiezana.toml.Toml;

import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String CONFIG_TOML= "config.toml";

    public static void main(String[] args) {

        Main main = new Main();
        InputStream stream = main.getClass().getClassLoader().getResourceAsStream(CONFIG_TOML);
        Toml toml = new Toml().read(stream);
        // Set a fallback if Log4j isn't found.
        FallbackLoggerConfiguration.setDebug(true);

        String token = toml.getString("token");
        String prefix = toml.getString("prefix");
        logger.info("Successfully read the bots token which is" + token);
        logger.info("The bots's prefix is" + prefix);


        // Login using the disocrd api
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();
                logger.info("Logged in as " + api.getClientId() + ", operating in " + api.getServers() + " servers.");
                api.setReconnectDelay(attempt -> attempt * 2);

        // Set the bots status

        api.updateActivity(ActivityType.WATCHING, "yes");

        // Register commands

        api.addMessageCreateListener(new HelpCommand());


        // Print the invite url of your bot
        logger.info("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}