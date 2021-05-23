package com.github.koxsosen.commands;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
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

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on" + Constants.SCRAPEURL)
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }
        message.getContent();
        channel.sendMessage("**Ducky** - `" +
                message.getAuthor().getName() + "`'s search query:");

            try {
                Document doc = null;
                doc = Jsoup.connect(Constants.SCRAPEURL + message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim().replace(" ", "%20")).timeout(0).get();
                logger.info(message.getAuthor() + " requested " + message.getContent().toLowerCase(Locale.ROOT).substring(Constants.PREFIX().length() + 1).trim()
                + " in " + channel.getIdAsString());

                Elements results = doc.getElementById("links").getElementsByClass("results_links");

                for(Element result: results){

                    Element title = result.getElementsByClass("links_main").first().getElementsByTag("a").first();
                    channel.sendMessage("**Title** - " + title.text());
                    channel.sendMessage("**Description** - " + result.getElementsByClass("result__snippet").first().text());
                    channel.sendMessage("**Link** - " +
                            title.attr("href"));
                }
            } catch (IOException e) {
                logger.warn(e);
            }
        }

}
