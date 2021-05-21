package com.github.koxsosen.commands;

import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class PasteCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(PasteCommand.class);

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageAuthor().isBotUser()) return;
        if (event.getMessageContent().toLowerCase().contains(Prefix.PREFIX() + "paste")) {
            event.getChannel().sendMessage("Hello! :wave: \nPlease use a paste service: " + Prefix.PASTEURL());

        }
    }

}
