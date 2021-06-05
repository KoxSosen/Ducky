package com.github.koxsosen.ai;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import io.paradaux.ai.MarkovMegaHal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;


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
        String filefound = "words.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource(filefound)).getFile());

        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Feed the cache into megahal
        megaHal.add(content);
        logger.info(content);

        // Get the generated sentence
        String sentence = megaHal.getSentence();
        logger.info(sentence);

        // Send the generated sentence
        channel.sendMessage(sentence);

        logger.info(message.getAuthor() + " requested this command.");
    }
}
