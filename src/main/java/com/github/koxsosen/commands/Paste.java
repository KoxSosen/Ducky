package com.github.koxsosen.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.github.koxsosen.Main.configuration;

public class Paste extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "paste")) {
                event.getChannel().sendMessage("**Ducky** - Hello! :wave: +" +
                        "\n Please use a paste service: " + configuration.getPasteurl()).queue();
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("paste")) {
            event.getHook().editOriginal("**Ducky** - Hello! :wave: +" +
                    "\n Please use a paste service: " + configuration.getPasteurl()).queue();
        }
    }

}
