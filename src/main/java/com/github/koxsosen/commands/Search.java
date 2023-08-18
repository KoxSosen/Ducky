package com.github.koxsosen.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.koxsosen.Main.configuration;
import static com.github.koxsosen.Main.pool;

public class Search extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "g")) {
                int maxLength = 500;

                pool.execute(() -> {
                    String messageContent = event.getMessage().getContentRaw()
                            .substring(configuration.getPrefix().length() + 1).trim();

                    // If the content is somehow empty, that means that they just executed .g without any parameters whatsoever.
                    if (messageContent.isEmpty()) {
                        event.getChannel().sendMessage("**Ducky** - No search query specified! - Example: `" + configuration.getPrefix() + "g car`").queue();
                        return;
                    }

                    // Here they exceed the max character limit, which is usually around ~500 in most search engines.
                    if (messageContent.length() > maxLength) {
                        event.getChannel().sendMessage("**Ducky** - The character limit is `" + maxLength + "` characters.").queue();
                        return;
                    }

                    // At this point, it should be safe to encode the text into a URL.
                    String formattedURL = URLEncoder.encode(messageContent, StandardCharsets.UTF_8);

                    // Craft the actual url that will be used to search
                    // https://engine.com/%20hi%safe=true%disableadverts=true...
                    String searchURL = configuration.getSearchURL() + formattedURL + configuration.getSafesearch() + configuration.getAdvertisements();

                    // Make the actual connection
                    try {
                        Document document = Jsoup.connect(searchURL).get();
                        Elements results = document.getElementById("links").getElementsByClass("results_links");
                        Element firstLink = results.get(0);
                        Element firstLinkTitle = firstLink.getElementsByClass("links_main").first().getElementsByTag("a").first();
                        Element firstLinkDescription = firstLink.getElementsByClass("result__snippet").first();

                        // We detect that no search results exist with the specified query by checking whether the first link has an empty title.
                        if (!firstLinkTitle.hasText()) {
                            event.getChannel().sendMessage("**Ducky** - No search results found!").queue();
                            return;
                        }

                        // Here we check if the first link description is even a thing, and if yes, replace all mentions to invalid ones.
                        String firstLinkStringDescription = (firstLinkDescription == null) ? "Unable to read the description." : firstLinkDescription.text().replaceAll("@", "@-");

                        // Here we just for safety replace all mentions to invalid ones in the page title.
                        String firstLinkStringTitle = firstLinkTitle.text().replaceAll("@", "@-");

                        event.getChannel().sendMessage("**Ducky** - `" + event.getMessage().getAuthor().getName() + "`'s search:" +
                                "\n**Title** - " + firstLinkStringTitle +
                                "\n**Description** - " + firstLinkStringDescription +
                                "\n**Link** - " + "<" + firstLinkTitle.attr("href") + ">").queue();


                    } catch (IOException e) {
                        // In case of any exceptions instead of silently failing, produce an error code and handle the exception.
                        event.getChannel().sendMessage("**Ducky** - The search parameters aren't valid.").queue();
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("g")) {
            event.deferReply().queue();
            int maxLength = 500;

            pool.execute(() -> {

                if (event.getOption("query") == null) {
                    event.getHook().editOriginal("**Ducky** - No search query specified! - Example: `" + configuration.getPrefix() + "g car`").queue();
                    return;
                }

                String messageContent = event.getOption("query").getAsString().trim();

                // If the content is somehow empty, that means that they just executed .g without any parameters whatsoever.
                if (messageContent.isEmpty()) {
                    event.getHook().editOriginal("**Ducky** - No search query specified! - Example: `" + configuration.getPrefix() + "g car`").queue();
                    return;
                }

                // Here they exceed the max character limit, which is usually around ~500 in most search engines.
                if (messageContent.length() > maxLength) {
                    event.getHook().editOriginal("**Ducky** - The character limit is `" + maxLength + "` characters.").queue();
                    return;
                }

                // At this point, it should be safe to encode the text into a URL.
                String formattedURL = URLEncoder.encode(messageContent, StandardCharsets.UTF_8);

                // Craft the actual url that will be used to search
                // https://engine.com/%20hi%safe=true%disableadverts=true...
                String searchURL = configuration.getSearchURL() + formattedURL + configuration.getSafesearch() + configuration.getAdvertisements();

                // Make the actual connection
                try {
                    Document document = Jsoup.connect(searchURL).get();
                    Elements results = document.getElementById("links").getElementsByClass("results_links");
                    Element firstLink = results.get(0);
                    Element firstLinkTitle = firstLink.getElementsByClass("links_main").first().getElementsByTag("a").first();
                    Element firstLinkDescription = firstLink.getElementsByClass("result__snippet").first();

                    // We detect that no search results exist with the specified query by checking whether the first link has an empty title.
                    if (!firstLinkTitle.hasText()) {
                        event.getHook().editOriginal("**Ducky** - No search results found!").queue();
                        return;
                    }

                    // Here we check if the first link description is even a thing, and if yes, replace all mentions to invalid ones.
                    String firstLinkStringDescription = (firstLinkDescription == null) ? "Unable to read the description." : firstLinkDescription.text().replaceAll("@", "@-");

                    // Here we just for safety replace all mentions to invalid ones in the page title.
                    String firstLinkStringTitle = firstLinkTitle.text().replaceAll("@", "@-");

                    event.getHook().editOriginal("**Ducky** - `" + event.getUser().getName() + "`'s search:" +
                            "\n**Title** - " + firstLinkStringTitle +
                            "\n**Description** - " + firstLinkStringDescription +
                            "\n**Link** - " + "<" + firstLinkTitle.attr("href") + ">").queue();


                } catch (IOException e) {
                    // In case of any exceptions instead of silently failing, produce an error code and handle the exception.
                    event.getHook().editOriginal("**Ducky** - The search parameters aren't valid.").queue();
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
