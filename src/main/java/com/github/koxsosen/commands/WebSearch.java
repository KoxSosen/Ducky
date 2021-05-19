package com.github.koxsosen.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class WebSearch implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().equalsIgnoreCase("!g")) {
            messageCreateEvent.getMessageContent().toLowerCase(Locale.ROOT);
                    messageCreateEvent.getMessageContent().trim()
                    .split("/ +/").toString();
        }
    }
}
