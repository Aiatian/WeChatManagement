package com.Aiatian.util.entity;

public class AccessToken {

    private  String accessToken;

    private  long expireTime;

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
         expireTime =  System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }


    public  boolean inExpired(){
        return System.currentTimeMillis()>expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
