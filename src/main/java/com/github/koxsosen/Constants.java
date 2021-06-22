/*
Ducky - A web search utility with other features.
Copyright (C) 2021 KoxSosen

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

package com.github.koxsosen;

public final class Constants {

    public static final String PREFIX = "."; // Prefix

    public static final String TOKEN = ""; // This is the loc of the token

    public static final String STATUS = ""; // Status

    public static final String STATUSTYPE = ""; // WATCHING / PLAYING

    public static final String PASTEURL = ""; // Paste url

    public static final String SCRAPEURL = ""; // The url to scrape

    public static final String ISSAFESEARCH = ""; // &kp=1

    public static final String PROXYHOST = "localhost"; // Proxy ip: eg 172.0.0.1

    public static final Integer PROXYPORT = 8080; // Proxy port. It's an integer, must be a value. eg 8080 or 8888

    public Constants() { }

    public static String TOKEN() {
        return TOKEN;
    }

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