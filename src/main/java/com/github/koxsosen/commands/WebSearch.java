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
    // String[] SEARCHQUERYVAR = new String[] {"SEARCQUERYVAR"}; // Soontm ( Hold the obj of the result in a variable )

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on the clearnet")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }
        message.getContent();
        channel.sendMessage(":warning: **Ducky is a under a recode.** :warning: \n Until this command is stable it is disabled for public use." +
                "\n But here is a google search query with your questions:" +
                "\n https://www.google.com/search?q=" + message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim().replace(" ", "%20"));

       //TODO
        // https://docs.oracle.com/javase/8/docs/api/java/net/URLEncoder.html
        // instead of replace maybe?

        logger.info(message.getAuthor() + " requested this command.");
    }
}
