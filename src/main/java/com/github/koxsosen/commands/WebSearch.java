package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;

public class WebSearch implements CommandExecutor {


    /*
    * Copyright (c) 2021, KoxSosen
    * All rights reserved.
    */

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on " + Constants.SCRAPEURL)
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

            try {
                Document doc = Jsoup.connect(Constants.SCRAPEURL
                        + message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim()
                        .replace(" ", "%20") + Constants.ISSAFESEARCH)
                        //.proxy(Constants.PROXYHOST(), Constants.PROXYPORT)
                        .get();

                // Check if it's empty, so we can return if it is.
                if (message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim()
                        .replace(" ", "%20").isEmpty()) {
                    channel.sendMessage("**No search query specified!** - Example: `" + Constants.PREFIX + "g car`");
                    return;
                }

                if (message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim()
                        .replace(" ", "%20").length() > 86) {
                    channel.sendMessage("**The max caracters you can search for is 86.**");
                    return;
                }

                logger.info(message.getAuthor().getId()
                        + " requested "
                        + message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim()
                        + " in " + channel.getId());

                Elements results = doc.getElementById("links").getElementsByClass("results_links");

                for (int i = 0, resultsSize = results.size(); i < resultsSize; i++) {

                    Element result = results.get(i);

                    Element title = result.getElementsByClass("links_main").first().getElementsByTag("a").first();
                    if (!title.hasText()) {
                        channel.sendMessage("**No search query found!**");
                        break;
                    }
                    new MessageBuilder()
                            .append("**Ducky** - `" + message.getAuthor().getName() + "`'s search query:")
                            .append("\n**Title** - " + title.text().replaceAll("@", "@-"))
                            .append("\n**Description** - " + result.getElementsByClass("result__snippet").first().text().replaceAll("@", "@-"))
                            .append("\n**Link** - <" + title.attr("href") + ">") // Don't show previews
                    .send(channel);
                    break;
                }
            } catch (IOException e) {
                logger.warn(e);
            }
        }
}
