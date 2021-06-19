
/*  Copyright (C) 2021 KoxSosen
Full notice of the license can be found under ../LICENSE */

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

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on " + Constants.SCRAPEURL)
    public void onCommand(TextChannel channel, Message message) {

        String content = message.getContent()
                .toLowerCase(Locale.ROOT)
                .substring(Constants.PREFIX().length() + 1)
                .trim()
                .replace(" ", "%20");

        int maxcharacters = 500;

        String executor = message.getAuthor().getIdAsString();

            try {
                Document doc = Jsoup.connect(Constants.SCRAPEURL + content + Constants.ISSAFESEARCH) // Change order
                        //.proxy(Constants.PROXYHOST(), Constants.PROXYPORT)
                        .get();

                if (content.isEmpty()) {
                    channel.sendMessage("**No search query specified!** - Example: `" + Constants.PREFIX + "g car`");
                    return;
                }

                if (content.length() > maxcharacters) {
                    channel.sendMessage("**Ducky** - The character limit is `" + maxcharacters + "` characters.");
                    return;
                }

                Elements results = doc.getElementById("links").getElementsByClass("results_links");

                for (int i = 0, resultsSize = results.size(); i < resultsSize; i++) {

                    Element result = results.get(i);

                    Element title = result.getElementsByClass("links_main").first().getElementsByTag("a").first();
                    if (!title.hasText()) {
                        channel.sendMessage("**Ducky** - No search results found!");
                        break;
                    }

                    new MessageBuilder()
                            .append("**Ducky** - `" + message.getAuthor().getName() + "`'s search query:")
                            .append("\n**Title** - " + title.text().replaceAll("@", "@-"))
                            .append("\n**Description** - " + result.getElementsByClass("result__snippet").first().text()
                                    .replaceAll("@", "@-"))
                            .append("\n**Link** - <" + title.attr("href") + ">") // Don't show previews

                    .send(channel);
                    break;
                }
            } catch (IOException e) {
                logger.warn(e);
            }
        logger.info(message.getAuthor() + " : " + content + " in " + channel.getId());
        }
}
