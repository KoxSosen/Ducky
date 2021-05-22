package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;


public class WebsiteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(WebsiteCommand.class);

    @Command(aliases = {Constants.PREFIX +"site"}, async = true, description = "Shows ducky's site")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

        channel.sendMessage("Ducky has it's own website! \n https://ducky.hahota.net");
        logger.info(message.getAuthor() + " requested this command.");
    }
}
