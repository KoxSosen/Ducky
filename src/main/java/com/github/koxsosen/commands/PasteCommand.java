package com.github.koxsosen.commands;

import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Locale;

public class PasteCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(PasteCommand.class);

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().toLowerCase().contains(Prefix.PREFIX() + "paste")) {
            messageCreateEvent.getChannel().sendMessage("Hello! :wave: /n Please use a paste service! /n" + Prefix.PASTEURL());
            logger.info("Successfully got arg(s): " + messageCreateEvent);
        }
    }

}
