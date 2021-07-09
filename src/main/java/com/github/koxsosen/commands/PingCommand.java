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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class PingCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(PingCommand.class);

    static String response = "zero";

    @Command(aliases = {Constants.PREFIX + "ping"}, async = true, description = "Ping an address")
    public void onCommand(TextChannel channel, Message message) throws IllegalStateException {

        String userarg = message.getContent()
                .toLowerCase(Locale.ROOT)
                .substring(Constants.PREFIX().length() + 4)
                .trim();

        // Make the request, and get a response request id + store the nodes.
        try {

            HttpURLConnection con = (HttpURLConnection) new URL("https://check-host.net/check-ping?host=" + userarg + "&max_nodes=3").openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();

            int status = con.getResponseCode();

            String messageresp = con.getResponseMessage();

            Object nodeone = "yes";

            switch (status) {

                case 199 -> {
                    channel.sendMessage("The server sent back an informational response." +
                            "Please contact Simon.#4921.");
                    logger.info(messageresp);
                }

                case 200 -> {

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    br.close();

                    if (sb.length() > 0) {
                        JSONObject jsonObject = new JSONObject(sb.toString());
                        response = jsonObject.getString("request_id");
                        nodeone = jsonObject.getJSONObject("nodes");
                    }

                    channel.sendMessage(response);

                    channel.sendMessage(nodeone.toString());

                }

                case 300 -> {
                    channel.sendMessage("The server tried to redirect our client." +
                            "Please contact Simon.#4921.");
                    logger.info(messageresp);
                }

                case 400 -> {
                    channel.sendMessage("Illegal address specified. " +
                            "Please contact Simon.#4921.");
                    logger.info(messageresp);
                }

                default -> logger.info("Illegal value: " + status);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        logger.info(message.getAuthor());

    }

    public static String RESPONSE() {
        return response;
    }

}
