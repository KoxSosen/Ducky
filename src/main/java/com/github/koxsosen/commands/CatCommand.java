package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CatCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(CatCommand.class);

    @Command(aliases = {Constants.PREFIX + "cat"}, async = true, description = "Get a random cat image from the web")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

        try {
             Connection connection = Jsoup.connect("https://api.thecatapi.com/v1/images/search/")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true);
                    connection.execute();

                     /// TODO - USE GSON ASAP!!!
                    // [{"breeds":[],"id":"2jPAIyDIJ","url":"https://cdn2.thecatapi.com/images/2jPAIyDIJ.png","width":996,"height":735}]
                    String regex = ".*(?<url>https?\\:\\/\\/\\w+(?:\\.(?:jpg|png))).+";
                    String data = connection.get().body().text().replaceAll(regex, "");


            logger.info(data);
            channel.sendMessage(data);

        } catch (IOException e) {
            logger.warn(e);
        }


        channel.sendMessage("**Ducky** - The cat command will be back soon!");
        logger.info(message.getAuthor() + " requested this command.");
    }


}

