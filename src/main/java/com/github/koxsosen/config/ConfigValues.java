package com.github.koxsosen.config;


import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface ConfigValues {

    @ConfKey("server.nick")
    @ConfComments("The IRC nick the bot will have.")
    @ConfDefault.DefaultString("Ducky")
    String nick();

    @ConfKey("server.host")
    @ConfComments("Server host, or address. Uses a string.")
    @ConfDefault.DefaultString("localhost")
    String host();

    // TODO: More constant values to config files.

}
