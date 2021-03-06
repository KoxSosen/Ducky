/*
Ducky - A web search utility with other features.
Copyright (C) 2021 KoxSosen

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class WebSearch implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    private final HashMap<Long, Long> cooldown = new HashMap <>();

    int maxcharacters = 500;

    String formatted = null;

    String search = null;

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on " + Constants.SCRAPEURL)
    public void onCommand(TextChannel channel, Message message) {

        if (cooldown.getOrDefault(message.getAuthor().getId(), 0L) > System.currentTimeMillis() - 4000) {
            channel.sendMessage("**Ducky** - Please wait `4` seconds before running this command again.");
            logger.info("Cooldown: " + message.getAuthor() + " in " + channel.getId());
            return;
        }

        cooldown.put(message.getAuthor().getId(), System.currentTimeMillis());

        String content = message.getContent()
                .substring(Constants.PREFIX().length() + 1).trim();

        if (content.isEmpty()) {
            channel.sendMessage("**Ducky** - No search query specified! - Example: `" + Constants.PREFIX + "g car`");
            return;
        }

        if (content.length() > maxcharacters) {
            channel.sendMessage("**Ducky** - The character limit is `" + maxcharacters + "` characters.");
            return;
        }

        formatted = URLEncoder.encode(content, StandardCharsets.UTF_8);

        search = new StringBuilder()
                .append(Constants.SCRAPEURL())
                .append(formatted)
                .append(Constants.ISSAFESERACH())
                .append(Constants.ADVERTS())
                .toString();

        try {
                Document doc = Jsoup.connect(search)
                        //.proxy(Constants.PROXYHOST(), Constants.PROXYPORT)
                        .get();

                Elements results = Objects.requireNonNull(doc.getElementById("links")).getElementsByClass("results_links");

                for (int i = 0, resultsSize = results.size(); i < resultsSize; i++) {

                    Element result = results.get(i);

                    Element title = Objects.requireNonNull(result.getElementsByClass("links_main").first()).getElementsByTag("a").first();

                    Element desc = result.getElementsByClass("result__snippet").first();
                    
                    if (!Objects.requireNonNull(title).hasText()) {
                        channel.sendMessage("**Ducky** - No search results found!");
                        break;
                    }
                    
                    String descText = (desc == null) ? "Unable to read the description." : desc.text().replaceAll("@", "@-");
                    
                    new MessageBuilder()
                            .append("**Ducky** - `" + message.getAuthor().getName() + "`'s search:")
                            .append("\n**Title** - " + title.text().replaceAll("@", "@-"))
                            .append("\n**Description** - " +  descText)
                            .append("\n**Link** - " + "<" + title.attr("href") + ">") // Don't show previews

                    .send(channel);
                    break;
                }
            } catch (IOException e) {
                logger.warn(e);
                logger.info(message.getAuthor());
                channel.sendMessage("**Ducky** - The search parameters aren't valid.");
                return; // If the url isn't valid do not try to run the rest of the code.
            }
        logger.info(message.getAuthor().getId() + " : (" + content + ") in " + channel.getId());
    }
}
