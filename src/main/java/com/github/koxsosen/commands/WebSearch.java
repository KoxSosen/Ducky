package com.github.koxsosen.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Locale;

public class WebSearch implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().equalsIgnoreCase("!g")) {
            messageCreateEvent.getMessageContent().toLowerCase(Locale.ROOT).trim().split(" +");
          //  logger.info("Successfully got arg(s): " + messageCreateEvent); Nope
        }
    }
}
