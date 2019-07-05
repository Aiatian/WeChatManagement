package com.Aiatian.util.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class ImageMessage  extends  BaseMessage{

    @XStreamAlias("MediaId")
    private  String mediaid;



    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    public ImageMessage(Map<String, String> requestMap,String mediaid) {
        super(requestMap);
        setMsgType("image");
        this.mediaid = mediaid;
    }
}
