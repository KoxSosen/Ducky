package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.io.IOException;

public class InviteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(InviteCommand.class);

    @Command(aliases = {Constants.PREFIX +"inv"}, async = true, description = "Create an invite for the bot")
    public void onComand(TextChannel channel, Message message) throws IOException {
        if (message.getAuthor().isBotUser()) {
            return;
        }

        channel.sendMessage("You can invite the bot using the following url: \n" + message.getApi().createBotInvite());
    }
}
