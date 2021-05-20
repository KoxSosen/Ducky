package com.github.koxsosen;

import com.github.koxsosen.commands.HelpCommand;
import com.github.koxsosen.commands.WebSearch;
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

    // Define the config file
    private static final String CONFIG_TOML= "config.toml";

    public static void main(String[] args) {

        @SuppressWarnings("InstantiationOfUtilityClass") Main main = new Main();
        InputStream stream = main.getClass().getClassLoader().getResourceAsStream(CONFIG_TOML);
        Toml toml = new Toml().read(stream);
        // Set a fallback if Log4j isn't found.
        FallbackLoggerConfiguration.setDebug(true);

        // Get the strings from the table of toml
        String token = String.valueOf(toml.getTable("essential").getString("token"));
        String prefix = String.valueOf(toml.getTable("essential").getString("prefix"));
        String activitytype = String.valueOf(toml.getTable("optional").getString("statusmethod"));
        String status = String.valueOf(toml.getTable("optional").getString("status"));

       // logger.info("Successfully read the bots token which is" + token);

        logger.info("The bots prefix is " + prefix);
        logger.info("The bots status is " + status + " and it's method is " + activitytype);

        // Login using the discord api
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();
                logger.info("Logged in as " + api.getYourself() + ", operating in " + api.getServers().size() + " servers.");
                api.setReconnectDelay(attempt -> attempt * 2);

        // Set the bots status
        api.updateActivity(ActivityType.valueOf(activitytype), status);

        // Register commands
        api.addMessageCreateListener(new HelpCommand());
        api.addMessageCreateListener(new WebSearch());

        // Print the invite url of your bot
        logger.info("You can invite the bot by using the following url: " + api.createBotInvite());
    }




}