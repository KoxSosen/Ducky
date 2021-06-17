package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;


public class WebsiteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(WebsiteCommand.class);

    @Command(aliases = {Constants.PREFIX + "site", Constants.PREFIX + "website"}, async = true, description = "Shows Ducky's site")
    public void onCommand(TextChannel channel, Message message) {

        new MessageBuilder()
                .append("Ducky has it's own website!")
                .append("\nLink: https://ducky.hahota.net")
                .append("\n\n" + "The bot's source code can be found here:")
                .append("\n" + "<https://github.com/KoxSosen/Ducky>")
                .send(channel);

        logger.info(message.getAuthor() + " requested this command.");
    }
}
