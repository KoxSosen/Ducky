package com.github.koxsosen;

import com.github.koxsosen.commands.HelpCommand;
import com.github.koxsosen.commands.InviteCommand;
import com.github.koxsosen.commands.WebSearch;
import com.github.koxsosen.info.Prefix;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Set a fallback if Log4j isn't found.
        FallbackLoggerConfiguration.setDebug(true);

        logger.info("The bots prefix is " + Prefix.PREFIX());
        logger.info("The bots status is " + Prefix.STATUS() + " and it's method is " + Prefix.STATUSMETHOD());

        // Login using the discord api
        DiscordApi api = new DiscordApiBuilder()
                .setToken(Prefix.TOKEN())
                .login().join();
                logger.info("Logged in as " + api.getYourself() + ", operating in " + api.getServers().size() + " servers.");
                api.setReconnectDelay(attempt -> attempt * 2);

        // Set the bots status
        api.updateActivity(ActivityType.valueOf(Prefix.STATUSMETHOD()), Prefix.STATUS());

        // Register commands
        api.addMessageCreateListener(new HelpCommand());
        api.addMessageCreateListener(new WebSearch());
        api.addMessageCreateListener(new InviteCommand());

        // Print the invite url of your bot
        logger.info("You can invite the bot by using the following url: " + api.createBotInvite());

        }

}