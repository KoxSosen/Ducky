package com.github.koxsosen.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.github.koxsosen.Main.configuration;
import static com.github.koxsosen.Main.jda;

public class Invite extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "invite")) {
                event.getChannel().sendMessage("**Ducky** - You can invite the bot using the following url: +" +
                        "\n" + "<" +
                        jda.getInviteUrl(Permission.MESSAGE_SEND, Permission.MESSAGE_HISTORY) +
                        ">").queue();
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("invite")) {
            event.deferReply().queue();
            event.getHook().editOriginal("**Ducky** - You can invite the bot using the following url: +" +
                    "\n" + "<" +
                    jda.getInviteUrl(Permission.MESSAGE_SEND, Permission.MESSAGE_HISTORY) +
                    ">").queue();
        }
    }

}
