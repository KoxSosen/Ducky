package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
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
        if (event.getMessageContent().toLowerCase().contains(Constants.PREFIX() + "help")) {
            if (event.getMessageAuthor().isBotUser()) return;
            event.getChannel().sendMessage("**Ducky** is mostly a web search utility for discord, but has other features too: " +
                            "\n \n :warning: - Web Search: `" + Constants.PREFIX() + "g` " +
                            "\n :x: - Random Cat Pic: `" + Constants.PREFIX() + "cat` " +
                    "\n :x: - Random Inspirational Quote: `" + Constants.PREFIX() + "quote` " +
                    "\n :warning: - Rock Paper Scissors: `" + Constants.PREFIX() + "rps` " +
                    "\n :white_check_mark: - Self Hosted Paste Server: `" + Constants.PREFIX() + "paste` " +
                    "\n :white_check_mark: - Ducky\\'s website: `" + Constants.PREFIX() + "site` " +
                    "\n :white_check_mark: - Invite the bot: `" + Constants.PREFIX() + "inv` " +
                    "\n :white_check_mark: - Help: `" + Constants.PREFIX() + "help`  " +
                    "\n \n Ducky is currently under a recode:" +
                            "\n :white_check_mark: - Works." +
                            "\n :warning: - Under develpment. " +
                            "\n :x: - Goal, not under develpment. " +
                            "\n \n Created by `Simon.#4921` with love.");
            logger.info(event.getMessage().getAuthor() + " requested this command." );
        }
    }
}
