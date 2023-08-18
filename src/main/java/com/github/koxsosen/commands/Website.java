package com.github.koxsosen.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.github.koxsosen.Main.configuration;

public class Website extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "website")) {
                event.getChannel().sendMessage("webisite command todo").queue();
            }
        }
    }

}
