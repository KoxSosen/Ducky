package com.github.koxsosen.commands;

import com.github.koxsosen.Main;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.koxsosen.Main.configuration;
import static com.github.koxsosen.Main.pool;

public class RandomDuck extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {

            if (event.getMessage().getContentRaw().startsWith(configuration.getPrefix() + "duck")) {

                pool.execute(() -> {
                    String imageUrl = "**Ducky** - No ducks were found!";

                    try {
                        HttpURLConnection con = (HttpURLConnection) new URL("https://random-d.uk/api/v2/random").openConnection();
                        try {
                            con.setRequestMethod("GET");
                        } catch (ProtocolException e) {
                            throw new RuntimeException(e);
                        }

                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                        String s = br.readLine();

                        if (s != null && s.length() > 0) {
                            JSONObject jsonObject = new JSONObject(s.trim());
                            imageUrl = jsonObject.getString("url");
                        }

                    } catch (IOException malformedURLException) {
                        malformedURLException.printStackTrace();
                    }
                    event.getChannel().sendMessage(imageUrl).queue();
                });


            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("duck")) {
            event.deferReply().queue();
            pool.execute(() -> {
                String imageUrl = "**Ducky** - No ducks were found!";

                try {
                    HttpURLConnection con = (HttpURLConnection) new URL("https://random-d.uk/api/v2/random").openConnection();
                    try {
                        con.setRequestMethod("GET");
                    } catch (ProtocolException e) {
                        throw new RuntimeException(e);
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                    String s = br.readLine();

                    if (s != null && s.length() > 0) {
                        JSONObject jsonObject = new JSONObject(s.trim());
                        imageUrl = jsonObject.getString("url");
                    }

                } catch (IOException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                event.getHook().editOriginal(imageUrl).queue();
            });
        }
    }

}
