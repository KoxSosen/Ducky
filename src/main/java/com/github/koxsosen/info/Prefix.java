package com.github.koxsosen.info;

import com.github.koxsosen.Main;
import com.moandjiezana.toml.Toml;

import java.io.InputStream;

// Attention! This object management is very bad. This will require a refactor which will be done in the future.


public class Prefix {

    private static final String CONFIG_TOML= "config.toml";

    static Main main = new Main();

    static InputStream stream = main.getClass().getClassLoader().getResourceAsStream(CONFIG_TOML);
    static Toml toml = new Toml().read(stream);

    public static String PREFIX() {
        return String.valueOf(toml.getTable("essential").getString("prefix"));
    }

    public static String TOKEN() {
        return String.valueOf(toml.getTable("essential").getString("token"));
    }

    public static String STATUSTYPE() {
        return String.valueOf(toml.getTable("optional").getString("statustype"));
    }

    public static String STATUS() {
        return String.valueOf(toml.getTable("optional").getString("status"));
    }

}


