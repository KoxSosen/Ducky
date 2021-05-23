package com.github.koxsosen;

public final class Constants {

    public static final String PREFIX = "d.";

    public static final String TOKEN = "";

    public static final String STATUS = ".g | .help";

    public static final String STATUSTYPE = "WATCHING";

    public static final String PASTEURL = "https://paste.hahota.net";

    public static final String SCRAPEURL = "https://duckduckgo.com/html/?q=";

    public static final String PROXYHOST = "127.0.1";

    public static final Integer PROXYPORT = 8888;

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

    public static String SCRAPEURL() {
        return  SCRAPEURL;
    }

    public static String PROXYHOST() {
        return PROXYHOST;
    }
    public static Integer PROXYPORT() {
        return PROXYPORT;
    }

}
