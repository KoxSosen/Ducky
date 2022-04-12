package com.github.koxsosen.config;


import org.apache.logging.log4j.core.appender.MemoryMappedFileAppender;
import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfHeader("https://github.com/KoxSosen/Ducky/")
public interface Config {

    @ConfKey("server.nick")
    @ConfComments("The IRC nick the bot will have.")
    @ConfDefault.DefaultString("Ducky")
    String nick();

    @ConfKey("server.host")
    @ConfComments("Server host, or address. Uses a string.")
    @ConfDefault.DefaultString("localhost")
    String host();

    @ConfKey("server.password")
    @ConfComments("Password used for SASL auth.")
    @ConfDefault.DefaultString("hunter12")
    String password();

    @ConfKey("server.channel")
    @ConfComments("The channel the bot should join.")
    @ConfDefault.DefaultString("#testing")
    String channel();

}
