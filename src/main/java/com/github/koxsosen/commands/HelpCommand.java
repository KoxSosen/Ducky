package com.github.koxsosen.commands;

import com.github.koxsosen.info.Prefix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class HelpCommand implements MessageCreateListener {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    // This is just a debug class to see if the bot is live and stuff
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        // TODO
        // Disable listeners here instead of returning if the user is a bot.
        if (event.getMessageContent().toLowerCase().contains(Prefix.PREFIX() + "help")) {
            if (event.getMessageAuthor().isBotUser()) return;
            event.getChannel().sendMessage("**Ducky** is mostly a web search utility for discord, but has other features too: " +
                            "\n \n :warning: - Web Search: `" + Prefix.PREFIX() + "g` " +
                            "\n :x: - Random Cat Pic: `" + Prefix.PREFIX() + "cat` " +
                    "\n :x: - Random Inspirational Quote: `" + Prefix.PREFIX() + "quote` " +
                    "\n :warning: - Rock Paper Scissors: `" + Prefix.PREFIX() + "rps` " +
                    "\n :white_check_mark: - Self Hosted Paste Server: `" + Prefix.PREFIX() + "paste` " +
                    "\n :white_check_mark: - Ducky\\'s website: `" + Prefix.PREFIX() + "site` " +
                    "\n :white_check_mark: - Invite the bot: `" + Prefix.PREFIX() + "inv` " +
                    "\n :white_check_mark: - Help: `" + Prefix.PREFIX() + "help`  " +
                    "\n \n Ducky is currently under a recode:" +
                            "\n :white_check_mark: - Works." +
                            "\n :warning: - Under develpment. " +
                            "\n :x: - Goal, not under develpment. " +
                            "\n \n Created by `Simon.#4921` with love.");
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }
}
