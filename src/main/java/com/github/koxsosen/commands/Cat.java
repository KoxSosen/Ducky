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
import net.engio.mbassy.listener.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONArray;
import org.kitteh.irc.client.library.event.client.ClientReceiveCommandEvent;
import org.kitteh.irc.client.library.feature.filter.CommandFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Cat {

    private static final Logger logger = LogManager.getLogger(Cat.class);

    @CommandFilter("CAT")
    @Handler
    public void onCatCommand(ClientReceiveCommandEvent event) {
        if (event.getCommand().equals(Constants.PREFIX() + "cat")) {

            String catimageUrl = "**Ducky** - No cats were found.";

            try {
                HttpURLConnection con = (HttpURLConnection) new URL("https://api.thecatapi.com/v1/images/search").openConnection();
                con.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
                String s = br.readLine();

                if (s != null && s.length() > 0) {
                    JSONArray jsonArray = new JSONArray(s.trim());
                    catimageUrl = jsonArray.getJSONObject(0).getString("url");
                }

            } catch (IOException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            event.getClient().sendMessage(event.getSource().toString(), catimageUrl);
            logger.info(event.getSource());
        }
    }
}

