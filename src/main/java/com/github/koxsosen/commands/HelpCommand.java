package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

public class HelpCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    @Command(aliases = {Constants.PREFIX +"help"}, async = true, description = "Help command for ducky")
    public void onCommand(TextChannel channel, Message message) {

        new MessageBuilder()
                .append("**Ducky** is mostly a web search utility for discord, but has other features too:")
                .append("\n \n - Web Search: `" + Constants.PREFIX() + "g` ")
                .append("\n - Random Cat Image: `" + Constants.PREFIX() + "cat` " )
                .append("\n - Random Duck Image: `" + Constants.PREFIX() + "duck`" )
                .append("\n - Random Dog Image: `" + Constants.PREFIX() + "dog`")
                .append("\n - Self Hosted Paste Server: `" + Constants.PREFIX() + "paste` " )
                .append("\n - Ducky\\'s website: `" + Constants.PREFIX() + "site` " )
                .append("\n - Invite the bot: `" + Constants.PREFIX() + "inv` " )
                .append("\n - Help: `" + Constants.PREFIX() + "help`  " )
                .append("\n \n Created by `287312849297080320` with love.")
                .send(channel);
        logger.info(message.getAuthor() + " requested this command."); // Add dynamic owner
    }
}