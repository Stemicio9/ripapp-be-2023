package it.ripapp.ripapp.onesignal;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.ripapp.ripapp.bll.Lang;


import java.util.*;

public class OSNotification {

    @JsonProperty(value = "app_id")
    private String appid;

    @JsonProperty(value = "include_external_user_ids")
    private List<String> externalIDs;

    @JsonProperty(value = "include_player_ids")
    private List<String> playerIDs;

    @JsonProperty
    private Map<Lang, String> contents;

    @JsonProperty
    private Map<Lang, String> headings;

    @JsonProperty
    private Map<Lang, String> subtitle;

    @JsonProperty
    private Map<String, String> data;

    @JsonProperty
    private String channel_for_external_user_ids;

    @JsonProperty
    private Boolean content_available;

    @JsonProperty
    private Integer priority;

    @JsonProperty
    private String ios_badgeType;

    @JsonProperty
    private String ios_badgeCount;

    @JsonProperty
    private String ios_sound;


    private OSNotification(Builder builder){
        playerIDs = builder.playerIDs;
        externalIDs = builder.externalids;
        contents = builder.contents;
        headings = builder.headings;
        subtitle = builder.subtitle;
        data = builder.data;
        content_available = builder.content_available;
        channel_for_external_user_ids = builder.channelForExternalUserIDs;
        priority = 10;
        ios_badgeType = builder.getIos_badgeType();
        ios_badgeCount = builder.getIos_badgeCount().toString();
        ios_sound = "";
    }

    OSNotification setAppID(String appid){
        this.appid = appid;
        return this;
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder
    {
        private List<String> externalids;
        private List<String> playerIDs;
        private Map<Lang, String> contents;
        private Map<Lang, String> headings;
        private Map<Lang, String> subtitle;
        private Map<String, String> data;
        private String channelForExternalUserIDs;
        private Boolean content_available;
        private String ios_badgeType;
        private Integer ios_badgeCount;

        public Builder() {
            playerIDs = new ArrayList<>();
            externalids = new ArrayList<>();
            contents = new HashMap<>();
            headings = new HashMap<>();
            subtitle = new HashMap<>();
            data = new HashMap<>();
            ios_badgeType = "None";
            ios_badgeCount = 0;
        }

        public Builder addContent(Lang lang, String value){
            contents.put(lang, value);
            return this;
        }

        public Builder addHeading(Lang lang, String value){
            headings.put(lang, value);
            return this;
        }

        public Builder addSubtitle(Lang lang, String value){
            subtitle.put(lang, value);
            return this;
        }

        public Builder addData(String key, String value){
            data.put(key, value);
            return this;
        }

        public Builder addExternalID(String externalid){
            externalids.add(externalid);
            return this;
        }

        public Builder addExternalIDs(Collection<String> externalids){
            this.externalids.addAll(externalids);
            return this;
        }

        public Builder setExternalIDs(Collection<String> externalids){
            this.externalids.clear();
            this.externalids.addAll(externalids);
            return this;
        }

        public Builder setPlayerIDs(Collection<String> playerIDs){
            this.playerIDs.clear();
            this.playerIDs.addAll(playerIDs);
            return this;
        }

        public Builder setContentAvailable(boolean content_available){
            this.content_available = content_available;
            return this;
        }

        public Builder setChannelForExternalUserIds(String channelForExternalUserIDs) {
            this.channelForExternalUserIDs = channelForExternalUserIDs;
            return this;
        }

        public String getIos_badgeType() {
            return ios_badgeType;
        }

        public Builder setIos_badgeType(String ios_badgeType) {
            this.ios_badgeType = ios_badgeType;
            return this;
        }

        public Integer getIos_badgeCount() {
            return ios_badgeCount;
        }

        public Builder setIos_badgeCount(Integer ios_badgeCount) {
            this.ios_badgeCount = ios_badgeCount;
            return this;
        }

        public OSNotification build(){
            return new OSNotification(this);
        }

    }
}
