package com.github.koxsosen.handler;

import net.engio.mbassy.listener.Handler;
import org.kitteh.irc.client.library.event.client.ClientReceiveCommandEvent;

public class CommandHandler {

    @Handler
    public void onCommand(ClientReceiveCommandEvent event) {

        // Returns the raw message
        String message = event.getRawMessage();

        // Todo: Create only one listener, and then use the global listener for messages.

    }
}
