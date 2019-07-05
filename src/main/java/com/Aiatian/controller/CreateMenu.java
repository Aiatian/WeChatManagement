package com.Aiatian.controller;

import com.Aiatian.services.impl.WeChatService;
import com.Aiatian.util.WechatUtil;
import com.Aiatian.util.entity.*;
import org.json.JSONObject;

public class CreateMenu {
    public static void main(String[] args) {
        Button btn = new Button();

        btn.getButton().add(new ClickButton("热门动漫电影","1"));

        btn.getButton().add(new ViewButton("百度","http://www.baidu.com"));

        SbuButton sb = new SbuButton("菜单");

        sb.getSub_button().add(new PhontoOrAlbumButtom("发送图片","31"));

        sb.getSub_button().add(new ClickButton("点击","32"));

        sb.getSub_button().add(new ViewButton("腾讯官网","https://www.qq.com/"));

        btn.getButton().add(sb);

        JSONObject json = new JSONObject(btn);



        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

       url = url.replace("ACCESS_TOKEN",WeChatService.getAccessToken());

        String post = WechatUtil.post(url, json.toString());

        System.out.println(post);


    }
}
