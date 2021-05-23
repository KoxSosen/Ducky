package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

public class HelpCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    // This is just a debug class to see if the bot is live and stuff
    @Command(aliases = {Constants.PREFIX +"help"}, async = true, description = "Help command for ducky")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

        channel.sendMessage("**Ducky** is mostly a web search utility for discord, but has other features too: " +
                "\n \n :white_check_mark: - **Web Search:** `" + Constants.PREFIX() + "g` " +
                "\n :white_check_mark: - Self Hosted Paste Server: `" + Constants.PREFIX() + "paste` " +
                "\n :white_check_mark: - Ducky\\'s website: `" + Constants.PREFIX() + "site` " +
                "\n :white_check_mark: - Invite the bot: `" + Constants.PREFIX() + "inv` " +
                "\n :white_check_mark: - Help: `" + Constants.PREFIX() + "help`  " +
                "\n :x: - Random Cat Pic: `" + Constants.PREFIX() + "cat` " +
                "\n :x: - Random Inspirational Quote: `" + Constants.PREFIX() + "quote` " +
                "\n :x: - Rock Paper Scissors: `" + Constants.PREFIX() + "rps` " +
                "\n \n Ducky is currently under a recode:" +
                "\n :white_check_mark: - Works." +
                "\n :warning: - Under development. " +
                "\n :x: - Goal, not under development. " +
                "\n \n Created by `" + "287312849297080320" + "` with love."); //TODO
        logger.info(message.getAuthor() + " requested this command."); // Add dynamic owner
    }
}