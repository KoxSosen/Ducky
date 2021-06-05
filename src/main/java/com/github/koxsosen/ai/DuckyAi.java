package com.github.koxsosen.ai;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import io.paradaux.ai.MarkovMegaHal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

public class DuckyAi implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(DuckyAi.class);

    // No prefix since we want to trigger the bot when it's mentioned.
    @Command(aliases = {"ducky"}, async = true, description = "Ai to generate messages")
    public void onCommand(TextChannel channel, Message message) {
        if (message.getAuthor().isBotUser()) {
            return;
        }

        // Create an instance for megahal
        MarkovMegaHal megaHal = new MarkovMegaHal();

        // Feed the cache into megahal
        megaHal.add(channel.getMessageCache().toString());

        // Get the generated sentence
        String sentence = megaHal.getSentence();

        // Send the generated sentence
        channel.sendMessage(sentence);

        logger.info(message.getAuthor() + " requested this command.");
    }
}
