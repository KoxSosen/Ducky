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
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;


public class InviteCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(InviteCommand.class);

    @Command(aliases = {Constants.PREFIX + "inv", Constants.PREFIX + "invite"}, async = true, description = "Create an invite for Ducky")
    public void onCommand(TextChannel channel, Message message) {

        new MessageBuilder()
                .append("**Ducky** - You can invite the bot using the following url:")
                .append("\n" + "<")
                .append(message.getApi().createBotInvite(new PermissionsBuilder().setAllowed(
                        PermissionType.MANAGE_CHANNELS,
                        PermissionType.SEND_MESSAGES,
                        PermissionType.READ_MESSAGE_HISTORY)
                        .build()))
                .append(">")
                .send(channel);

        logger.info(message.getAuthor());
    }
}
