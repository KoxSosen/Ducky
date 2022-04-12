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

package com.github.koxsosen;

import com.github.koxsosen.commands.Cat;
import com.github.koxsosen.commands.Dog;
import com.github.koxsosen.commands.Duck;
import com.github.koxsosen.config.ConfigManager;
import com.github.koxsosen.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.feature.auth.SaslPlain;

import java.nio.file.Paths;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ConfigManager <Config> configManager = ConfigManager.create(Paths.get("."), "config.yml", Config.class);
        configManager.reloadConfig(); // Oh, yeah we reload before we load data.

        Config config = configManager.getConfigData();

        Client client = Client.builder()
                .nick(config.nick())
                .server()
                .host(config.host())
                .then().build();

        // SASL, this should not be used without TLS.
        client.getAuthManager()
                .addProtocol(new SaslPlain(client, config.nick(), config.password()));

        // Manages listeners.
        client.getEventManager().registerEventListener(new Cat());
        client.getEventManager().registerEventListener(new Dog());
        client.getEventManager().registerEventListener(new Duck());

        client.addChannel(config.channel());

        logger.info("The bots prefix is " + Constants.PREFIX());

        }
}