package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;


public class PasteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(PasteCommand.class);

    @Command(aliases = {Constants.PREFIX +"paste"}, async = true, description = "Shows duckys paste")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

       channel.sendMessage("Hello! :wave: \nPlease use a paste service: " + Constants.PASTEURL());
        logger.info(message.getAuthor() + " requested this command.");
    }
}