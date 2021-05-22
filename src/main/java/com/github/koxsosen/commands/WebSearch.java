package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.util.Locale;

public class WebSearch implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on the clearnet")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }
        message.getContent().toLowerCase(Locale.ROOT).trim().split(" +"); // This returns a String[] ( array )
        channel.sendMessage(":warning: **Ducky is a under a recode.** :warning: \n Until this command is stable it is disabled for public use.");
        logger.info(message.getAuthor() + " requested this command.");
    }
}
