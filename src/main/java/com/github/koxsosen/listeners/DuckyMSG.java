package com.github.koxsosen.listeners;

import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class DuckyMSG implements MessageCreateListener {

    private final CommandHandler commandHandler;

    public DuckyMSG(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageAuthor().isBotUser() | event.getMessageAuthor().isWebhook()) {
            return;
        }
    }
}
