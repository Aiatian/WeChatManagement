package com.Aiatian.util.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class TextMessage extends  BaseMessage{

    @XStreamAlias("Content")
    private  String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public TextMessage(Map<String, String> requestMap) {
        super(requestMap);
    }

    public TextMessage(Map<String,String> requestMap, String content) {
        super(requestMap);
        // 设置文本消息的msgType 为 text
        setMsgType("text");

        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage[content="+content+",getToUserName()="+getToUserName()+",getFromUserName()="+getFromUserName()+",getCreateTime()="+getCreateTime()+"]";
    }
}
