package com.github.koxsosen.commands;


import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class HelpCommand implements MessageCreateListener {


    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("!help")) {
            event.getChannel().sendMessage("This is a test debug message which was sent at " + event.getChannel().getCreationTimestamp());
        }
    }
}

