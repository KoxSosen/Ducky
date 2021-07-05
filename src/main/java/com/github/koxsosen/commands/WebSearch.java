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
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class WebSearch implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(WebSearch.class);

    private final HashMap<Long, Long> cooldown = new HashMap <>();

    @Command(aliases = {Constants.PREFIX + "g"}, async = true, description = "Runs a web search on " + Constants.SCRAPEURL)
    public void onCommand(TextChannel channel, Message message) {

        if (cooldown.getOrDefault(message.getAuthor().getId(), 0L) > System.currentTimeMillis() - 4000) {
            channel.sendMessage("**Ducky** - Please wait `4` seconds before running this command again.");
            logger.info("Cooldown: " + message.getAuthor() + " in " + channel.getId());
            return;
        }

        cooldown.put(message.getAuthor().getId(), System.currentTimeMillis());

        String content = message.getContent()
                .toLowerCase(Locale.ROOT)
                .substring(Constants.PREFIX().length() + 1)
                .trim()
                .replace(" ", "%20");

        int maxcharacters = 500;

        Random obj = new Random(); // make obj random
        int rand_num = obj.nextInt(0xffffff + 1); // If a number starts with 0x, it means the rest of the digits are interpreted as hex.
        String colorCode = String.format("#%06x", rand_num); // format the hex code

         try {
                Document doc = Jsoup.connect(Constants.SCRAPEURL + content + Constants.ISSAFESEARCH) // Change order
                        //.proxy(Constants.PROXYHOST(), Constants.PROXYPORT)
                        .get();

                if (content.isEmpty()) {
                    channel.sendMessage("**Ducky** - No search query specified! - Example: `" + Constants.PREFIX + "g car`");
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

                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle(title.text().replaceAll("@", "@-"))
                            .setDescription(result.getElementsByClass("result__snippet").first().text()
                                    .replaceAll("@", "@-"))
                            .setColor(Color.decode(colorCode))
                            .addField(title.attr("href"),"_ _")
                            .setFooter("Requested by: " + message.getAuthor().getDisplayName());
                    channel.sendMessage(embed);
                    break;
                }

            } catch (IOException e) {
                logger.warn(e);
            }
        logger.info(message.getAuthor() + " : [ " + content + " ] in " + channel.getId());
        }
}
