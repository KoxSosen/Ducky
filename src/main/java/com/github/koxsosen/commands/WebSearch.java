package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Locale;

public class WebSearch implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(WebSearch.class);


    // This class gets the args from the !g command. This will be used to run the search query.

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageAuthor().isBotUser()) return;
        if (event.getMessageContent().toLowerCase().contains(Constants.PREFIX() + "g")) {
            event.getMessageContent().toLowerCase(Locale.ROOT).trim().split(" +"); // This creates a string array & we have to create a String array
            event.getChannel().sendMessage(":warning: **Ducky is a under a recode.** :warning: \n Until this command is stable it is disabled for public use.");
          logger.info("Successfully got arg(s): " + event.getMessageContent());
        }
    }
}
