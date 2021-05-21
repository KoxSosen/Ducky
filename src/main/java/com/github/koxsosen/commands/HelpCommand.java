package com.github.koxsosen.commands;

import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class HelpCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    // This is just a debug class to see if the bot is live and stuff
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().toLowerCase().contains(Prefix.PREFIX() + "help")) {
            event.getChannel().sendMessage("This is a test debug message which was sent at " + event.getMessage().getCreationTimestamp() + " by " + event.getMessage().getAuthor().getName());
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }
}

