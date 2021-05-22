package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class WebsiteCommand implements MessageCreateListener {


    private static final Logger logger = LogManager.getLogger(WebsiteCommand.class);

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().toLowerCase().contains(Constants.PREFIX() + "site")) {
            if (event.getMessageAuthor().isBotUser()) return;
            event.getChannel().sendMessage("Ducky has it's own website! \nhttps://ducky.hahota.net");
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }


}
