package com.github.koxsosen;

public final class Constants {

    public static final String PREFIX = "q.";

    public static final String TOKEN = "token";

    public static final String STATUS = "q.g | q.help";

    public static final String STATUSTYPE = "WATCHING";

    public static final String PASTEURL = "https://paste.ttr3.eu";

    private Constants() { }

    public static String STATUS() {
        return STATUS;
    }

    public static String PREFIX() {
        return PREFIX;
    }

    public static String STATUSTYPE() {
        return STATUSTYPE;
    }

    public static String PASTEURL() {
        return PASTEURL;
    }
}
