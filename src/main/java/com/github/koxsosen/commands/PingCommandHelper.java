package com.github.koxsosen.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PingCommandHelper {

    static String ponsetwo = "";

    private static final Logger logger = LogManager.getLogger(PingCommandHelper.class);

    public static void helper() {


        try {

            String base = "https://check-host.net/check-result/";

            String addon = PingCommand.RESPONSE();

            String url = base + addon;

            URL urltest = new URL(url);

            // TODO - Fix parsing.

            HttpURLConnection con = (HttpURLConnection) (urltest).openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();

            int yea = con.getResponseCode();

            if (yea == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                br.close();

                logger.info(sb);

                if (sb.length() > 0) {
                    JSONObject jsonObject = new JSONObject(sb.toString());
                    ponsetwo = jsonObject.toString();

                    logger.info(jsonObject);
                    logger.info(ponsetwo);

                }


            }
            // TODO - Get the response here as well, serialize, and send back to the user.

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static String PONSE() {
        return ponsetwo;
    }

}
