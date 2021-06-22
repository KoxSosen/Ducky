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


public class PasteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(PasteCommand.class);

    @Command(aliases = {Constants.PREFIX +"paste"}, async = true, description = "Shows Ducky's paste")
    public void onCommand(TextChannel channel, Message message) {

       channel.sendMessage("**Ducky** - Hello! :wave: " +
               "\nPlease use a paste service: " + Constants.PASTEURL());
        logger.info(message.getAuthor());
    }
}