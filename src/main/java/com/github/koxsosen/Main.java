package com.github.koxsosen;

import com.github.koxsosen.commands.*;
import com.github.koxsosen.configuration.HoconConfiguration;
import com.github.koxsosen.configuration.LoadAndCreate;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.concurrent.ExecutorService;

public abstract class Main {
    public static JDA jda;
    public static HoconConfiguration configuration;
    public static final int NUM_CORES = Runtime.getRuntime().availableProcessors();

    // Since we are sometimes waiting for a request to complete, or a web engine to respond, we can't keep blocking the main JDA thread.
    public static ExecutorService pool;

    public static void main(String[] args) {
        configuration = LoadAndCreate.initialiseConfiguration();
        pool = LoadAndCreate.createFixedThreadPool();

        jda = JDABuilder.createLight(configuration.getToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Help())
                .addEventListeners(new Search())
                .addEventListeners(new RandomCat())
                .addEventListeners(new RandomDog())
                .addEventListeners(new RandomDuck())
                .addEventListeners(new Invite())
                .addEventListeners(new Paste())
                .addEventListeners(new Website())
                .build();

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jda.getGuildById(1142003887004463115L)
                .updateCommands().addCommands(
                        Commands.slash("help", "Help command from Ducky."),
                        Commands.slash("website", "Ducky's website."),
                        Commands.slash("paste", "Ducky's paste site."),
                        Commands.slash("cat", "Get a random cat image."),
                        Commands.slash("dog", "Get a random dog image."),
                        Commands.slash("duck", "Get a random duck image."),
                        Commands.slash("g", "Run a search query.")
                                .addOption(OptionType.STRING, "query", "The parameters to query for."),
                        Commands.slash("invite", "Get an invite for Ducky.")

                ).queue();
    }

}