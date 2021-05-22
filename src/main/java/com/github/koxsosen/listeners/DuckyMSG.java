package com.github.koxsosen.listeners;


import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.CommandHandler;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Arrays;

public class DuckyMSG implements MessageCreateListener {

    private final CommandHandler commandHandler;

    public DuckyMSG(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageAuthor().isBotUser()) {
            return;
        }
        boolean messageIsCommand = commandHandler.getCommands().stream()
                .flatMap(command -> Arrays.stream(command.getCommandAnnotation().aliases()))
                .anyMatch(alias -> event.getMessageContent().contains(alias));
    }
}
