package com.github.koxsosen.config;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface ConfigValues {

    @ConfKey("main.token")
    @ConfComments("The token of the bot.")
    @ConfDefault.DefaultString("bottoken")
    String token();

    @ConfKey("main.prefix")
    @ConfComments("The prefix of the bot.")
    @ConfDefault.DefaultString(".")
    String prefix();
}
