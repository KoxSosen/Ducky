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
import org.javacord.api.entity.message.MessageBuilder;

public class HelpCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(HelpCommand.class);

    @Command(aliases = {Constants.PREFIX + "help", Constants.PREFIX + "halp"}, async = true, description = "Help command for ducky")
    public void onCommand(TextChannel channel, Message message) {

        new MessageBuilder()
                .append("**Ducky** is mostly a web search utility for discord, but has other features too:")
                .append("\n \n - Web Search: `" + Constants.PREFIX() + "g` ")
                .append("\n - Random Cat Image: `" + Constants.PREFIX() + "cat` " )
                .append("\n - Random Duck Image: `" + Constants.PREFIX() + "duck`" )
                .append("\n - Random Dog Image: `" + Constants.PREFIX() + "dog`")
                .append("\n - Self Hosted Paste Server: `" + Constants.PREFIX() + "paste` " )
                .append("\n - Ducky\\'s website: `" + Constants.PREFIX() + "site` " )
                .append("\n - Invite the bot: `" + Constants.PREFIX() + "inv` " )
                .append("\n - Help: `" + Constants.PREFIX() + "help`  " )
                .append("\n \n Created by `Simon.#4921 [287312849297080320]` with :heart:")
                .send(channel);
        logger.info(message.getAuthor());
    }
}