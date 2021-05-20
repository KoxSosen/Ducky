package com.github.koxsosen.commands;

import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class InviteCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(InviteCommand.class);

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().toLowerCase().contains(Prefix.PREFIX() + "inv")) {
            event.getChannel().sendMessage("You can invite the bot by using the following url: " + event.getApi().createBotInvite());
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }

}
