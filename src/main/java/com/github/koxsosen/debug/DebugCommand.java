/*
Ducky - A web search utility with other features.
Copyright (C) 2022 KoxSosen

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

import java.util.concurrent.TimeUnit;


public class DebugCommand implements CommandExecutor {

    private static final Logger logger = LogManager.getLogger(DebugCommand.class);

    private final long startTime = System.currentTimeMillis();

    @Command(aliases = {Constants.PREFIX +"debug"}, async = true, description = "Debug command for ducky")
    public void onCommand(TextChannel channel, Message message, DiscordApi api) {
        if (!message.getAuthor().isBotOwner()) {
            channel.sendMessage("**Ducky** - You can't use this command.");
            return;
        }

        long ms = System.currentTimeMillis() - startTime;
        long days = TimeUnit.MILLISECONDS.toDays(ms);
        ms -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(ms);
        ms -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        ms -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);

        int maxmem = 1024*1024;

        new MessageBuilder()
                .append("**Ducky** - Debug Information:")
                .append("\n \n Uptime: `"+ days + "` d `" + hours + "` h `" + minutes + "` m `" +   seconds  +  "` s" + ".")
                .append("\n Used Memory: `" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/maxmem + "` mb.")
                .append("\n Servers: `" + api.getServers().size() + "`")
                .send(channel);

        logger.info(message.getAuthor());
    }
}