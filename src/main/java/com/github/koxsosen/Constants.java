package com.github.koxsosen;

public final class Constants {

    public static final String PREFIX = "d."; // Prefix

    public static final String TOKEN = ""; // This is the loc of the token

    public static final String STATUS = "d.g | d.help"; // Status

    public static final String STATUSTYPE = "PLAYING"; // WATCHING / PLAYING

    public static final String PASTEURL = "https://ducky.hahota.net"; // Paste url

    public static final String SCRAPEURL = "https://duckduckgo.com/html/?q="; // The url to scrape, eg https://duckduckgo.com/html/?q=

    public static final String ISSAFESEARCH = "&kp=1";

    public static final String PROXYHOST = "localhost"; // Proxy ip: eg 172.0.0.1

    public static final Integer PROXYPORT = 8080; // Proxy port. It's an integer, must be a value. eg 8080 or 8888

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
    public static String ISSAFESERACH() { // could be an integer but meh
        return ISSAFESEARCH;
    }
}
