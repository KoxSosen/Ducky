package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CatCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(CatCommand.class);

    @Command(aliases = {Constants.PREFIX + "cat"}, async = true, description = "Get a random cat image from the web")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }


        channel.sendMessage("**Ducky** - The cat command will be back soon!");
        logger.info(message.getAuthor() + " requested this command.");
    }


}

