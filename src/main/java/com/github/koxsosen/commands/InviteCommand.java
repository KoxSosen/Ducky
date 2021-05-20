package com.github.koxsosen.commands;

import com.github.koxsosen.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class InviteCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(InviteCommand.class);

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().contains(Main.PREFIX + "inv")) {
            event.getChannel().sendMessage("You can invite the bot by clicking the following url:" + event.getChannel().getCreationTimestamp());
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }

}
