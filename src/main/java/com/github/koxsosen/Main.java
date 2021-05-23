package com.github.koxsosen;


import com.github.koxsosen.commands.*;
import com.github.koxsosen.listeners.DuckyMSG;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // Set a fallback if Log4j isn't found.
        FallbackLoggerConfiguration.setDebug(false);

        logger.info("The bots prefix is " + Constants.PREFIX());
        logger.info("The bots status is " + Constants.STATUS() + " and it's method is " + Constants.STATUSTYPE());

        // Login using the discord api
        DiscordApi api = new DiscordApiBuilder()
                .setToken(Constants.TOKEN)
                .setAllIntentsExcept(Intent.GUILD_EMOJIS, Intent.GUILD_BANS, Intent.GUILD_INVITES, Intent.DIRECT_MESSAGES) // Disable unneeded Intents.
                .setWaitForServersOnStartup(false)
                .login().join();
                logger.info("Logged in as " + api.getYourself() + ", operating in " + api.getServers().size() + " servers.");
                // If the bot disconnects always reconnect with a 2*sec delay. ( 1st: 2s, 2nd:4s )
                api.setReconnectDelay(attempt -> attempt * 2);
                // Only cache 10 messages per channel & remove ones older than 1 hour.
                api.setMessageCacheSize(10, 60*60);

        // Set the bots status
        api.updateActivity(ActivityType.valueOf(Constants.STATUSTYPE), Constants.STATUS());

        // Register commands

        CommandHandler handler = new JavacordHandler(api);
        handler.registerCommand(new WebsiteCommand());
        handler.registerCommand(new InviteCommand());
        handler.registerCommand(new PasteCommand());
        handler.registerCommand(new HelpCommand());
        handler.registerCommand(new WebSearch());

        api.addMessageCreateListener(new DuckyMSG(handler));

        // Print the invite url of your bot
        logger.info("You can invite the bot by using the following url: " + api.createBotInvite());

        }

}