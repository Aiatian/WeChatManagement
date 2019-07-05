package com.Aiatian.util.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

public class Music  extends  BaseMessage{

    @XStreamAlias("Title")
    private  String title;

    @XStreamAlias("Description")
    private  String description;

    @XStreamAlias("MusicUrl")
    private  String musicUrl;

    @XStreamAlias("HQMsicUrl")
    private  String hQMsicUrl;

    @XStreamAlias("ThumbMediaId")
    private  String thumbMediaId;

    public Music(Map<String, String> requestMap, String title, String description, String musicUrl, String hQMsicUrl, String thumbMediaId) {
        super(requestMap);
        this.title = title;
        this.description = description;
        this.musicUrl = musicUrl;
        this.hQMsicUrl = hQMsicUrl;
        this.thumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMsicUrl() {
        return hQMsicUrl;
    }

    public void sethQMsicUrl(String hQMsicUrl) {
        this.hQMsicUrl = hQMsicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
