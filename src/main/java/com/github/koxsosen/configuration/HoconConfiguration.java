package com.github.koxsosen.configuration;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@ConfigSerializable
public class HoconConfiguration {

    @Comment("Discord API token.")
    private String token = "";

    public @Nullable String getToken() {
        return this.token;
    }

    @Comment("Prefix that is used to call commands from the bot.")
    private String prefix = ".";

    public @Nullable String getPrefix() {
        return this.prefix;
    }

    @Comment("Adds the safe search parameter to the search URL commonly recognised by search engines.")
    private String safesearch = "&kp=1";

    public @Nullable String getSafesearch() {
        return this.safesearch;
    }

    @Comment("Sets whether to enable advertisements or not.")
    private String advertisements = "&k1=-1";

    public @Nullable String getAdvertisements() {
        return this.advertisements;
    }

    @Comment("Sets the URL that will be used to run the search queries.")
    private String searcurl = "https://duckduckgo.com/html/?q=";

    public @Nullable String getSearchURL() {
        return this.searcurl;
    }

    @Comment("Sets the paste url that will be used in the paste command.")
    private String pasteurl = "https://paste.hahota.net/";

    public @Nullable String getPasteurl() {
        return this.pasteurl;
    }

}
