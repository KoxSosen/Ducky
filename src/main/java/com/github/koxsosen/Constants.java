package com.github.koxsosen;

public final class Constants {

    public static final String PREFIX = ""; // Prefix

    public static final String TOKEN = ""; // This is the loc of the token

    public static final String STATUS = ""; // Status

    public static final String STATUSTYPE = ""; // WATCHING / PLAYING

    public static final String PASTEURL = ""; // Paste url

    public static final String SCRAPEURL = ""; // The url to scrape, eg https://duckduckgo.com/html/?q=

    public static final String PROXYHOST = ""; // Proxy ip: eg 172.0.0.1

    public static final Integer PROXYPORT = 0; // Proxy port. It's an integer, must be a value. eg 8080 or 8888

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
        return SCRAPEURL;
    }

    public static String PROXYHOST() {
        return PROXYHOST;
    }
    public static Integer PROXYPORT() {
        return PROXYPORT;
    }

}
