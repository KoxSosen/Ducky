package com.github.koxsosen.commands;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PingCommandHelper {

    public void helper() {

        try {

            HttpURLConnection con = (HttpURLConnection) new URL(" https://check-host.net/check-result/" + PingCommand.RESPONSE()).openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();

            // TODO - Get the response here as well, serialize, and send back to the user.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
