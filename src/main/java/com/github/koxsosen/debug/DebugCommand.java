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

package com.github.koxsosen.debug;

import com.github.koxsosen.Constants;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;


public class DebugCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(DebugCommand.class);

    RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();

    @Command(aliases = {Constants.PREFIX +"debug"}, async = true, description = "Debug command for ducky")
    public void onCommand(TextChannel channel, Message message, DiscordApi api) {
        if (!message.getAuthor().isBotOwner()) {
            channel.sendMessage("**Ducky** - You can't use this command.");
            return;
        }

        long uptime = bean.getUptime();
        long uptimemin = TimeUnit.MILLISECONDS.toMinutes(uptime);
        long uptimehr = TimeUnit.MILLISECONDS.toHours(uptime);

        int dataSize = 1024*1024;

        new MessageBuilder()
        .append("**Ducky** - Debug Information:")
                .append("\n \n Uptime: `" + uptimehr + "` h or `" + uptimemin + "` min.")
                .append("\n Used Memory: `" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/dataSize + "` mb.")
                .append("\n Servers: `" + api.getServers().size() + "`")
                .send(channel);

        logger.info(message.getAuthor());
    }
}