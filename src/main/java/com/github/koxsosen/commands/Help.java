package com.github.koxsosen.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.github.koxsosen.Main.configuration;

public class Help extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "help")) {
                event.getChannel().sendMessage("**Ducky** is mostly a web search utility for discord, but has other features too:" +
                        "\n \n- Web Search: `" + configuration.getPrefix() + "g`" +
                        "\n- Random Cat Image: `" + configuration.getPrefix() + "cat`" +
                        "\n- Random Duck Image: `" + configuration.getPrefix() + "duck`" +
                        "\n- Random Dog Image: `" + configuration.getPrefix() + "dog`" +
                        "\n- Self Hosted Paste Server: `" + configuration.getPrefix() + "paste`" +
                        "\n- Ducky\\'s website: `" + configuration.getPrefix() + "site`" +
                        "\n- Invite the bot: `" + configuration.getPrefix() + "invite`" +
                        "\n- Help: `" + configuration.getPrefix() + "help`" +
                        "\n\nCreated by <@287312849297080320> with :heart:").queue();
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
            event.deferReply().queue();
            event.getHook().editOriginal("**Ducky** is mostly a web search utility for discord, but has other features too:" +
                    "\n \n- Web Search: `" + configuration.getPrefix() + "g`" +
                    "\n- Random Cat Image: `" + configuration.getPrefix() + "cat`" +
                    "\n- Random Duck Image: `" + configuration.getPrefix() + "duck`" +
                    "\n- Random Dog Image: `" + configuration.getPrefix() + "dog`" +
                    "\n- Self Hosted Paste Server: `" + configuration.getPrefix() + "paste`" +
                    "\n- Ducky\\'s website: `" + configuration.getPrefix() + "site`" +
                    "\n- Invite the bot: `" + configuration.getPrefix() + "invite`" +
                    "\n- Help: `" + configuration.getPrefix() + "help`" +
                    "\n\nCreated by <@287312849297080320> with :heart:").queue();
        }
    }




}
