package com.github.koxsosen;

import com.github.koxsosen.commands.Help;
import com.github.koxsosen.commands.Search;
import com.github.koxsosen.configuration.HoconConfiguration;
import com.github.koxsosen.configuration.LoadAndCreate;
import com.github.koxsosen.listeners.Message;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTMLEditorKit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

                .build();
    }

}