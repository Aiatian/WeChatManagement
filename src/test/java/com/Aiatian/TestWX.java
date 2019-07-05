package com.Aiatian;

import com.Aiatian.services.impl.WeChatService;
import com.Aiatian.util.entity.*;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;

public class TestWX {


    @Test
    public  void  testButton(){
        Button btn = new Button();

        btn.getButton().add(new ClickButton("一级点击","1"));
        btn.getButton().add(new ViewButton("一级跳转","http://www.baidu.com"));

        SbuButton sb = new SbuButton("有子菜单");

        sb.getSub_button().add(new PhontoOrAlbumButtom("传图","31"));

        sb.getSub_button().add(new ClickButton("点击","32"));

        sb.getSub_button().add(new ViewButton("网易新闻","http://news.163.com"));

        btn.getButton().add(sb);

        JSONObject json = new JSONObject(btn);

        System.out.println(json.toString());
    }


    @Test
    public  void  testToken(){

        System.out.println(WeChatService.getAccessToken());
        System.out.println(WeChatService.getAccessToken());

    }


    @Test
    public  void  testCode(){
        String  []msg = {"新闻","好新闻的","今天的新闻","昨天的新闻","最近的新闻","新的最近的新闻","新1闻"};

        for (int i = 0; i < msg.length; i++) {
            String s = msg[i];
            // matches 模糊关键字
            System.out.println(s.matches(".*新闻.*"));
        }


    }


    @Test
    public  void  testCode2(){

        int []num = {1,2,3,0,40,2,3,5,1,0,0,0,2} ;

       // Arrays.sort(num);


        for (int i : num) {
            System.out.print(i+",");
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num.length; i++) {
           list.add(num[i]);
            if (num[i]==0){
                list.remove(i);
            }
        }







    }


}
