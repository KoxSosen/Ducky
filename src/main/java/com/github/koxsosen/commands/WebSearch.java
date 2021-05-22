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
   // String[] SEARCQUERYVAR = new String[] {"Door"}; // Soontm ( Hold the obj of the result in a variable )

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on the clearnet")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }
        message.getContent().toLowerCase(Locale.ROOT).trim().split(" +"); // This returns a String[] ( String array )
        channel.sendMessage(":warning: **Ducky is a under a recode.** :warning: \n Until this command is stable it is disabled for public use." +
                "\n But here is a google search query with your questions:" +
                "\n https://www.google.com/search?q=");
        logger.info(message.getAuthor() + " requested this command.");
    }
}
