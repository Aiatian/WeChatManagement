package com.Aiatian.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WechatUtil {

    //配置你申请的Key
    public  static  final String  APPKEY="*************";

    private  static  final  String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private  static  final String APPID="wxb8f2515ffd919ad7";

    private  static  final String APPSECRET="6f23203a9a6d5ae8948d81ae7df18af3";

    public static  String get(String url){
        URL UrlObj;
        try {
            UrlObj= new URL(url);
            URLConnection urlConnection = UrlObj.openConnection();

            InputStream is =  urlConnection.getInputStream();

            byte[] b = new byte[1024];

            int len;

            StringBuilder sb = new StringBuilder();

            while ((len=is.read(b))!= -1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  null;
    }


    public  static  String post(String url,String data){

        try {
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            // 开启 发送数据 默认是不发送
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();

            os.write(data.getBytes());

            os.close();

            InputStream is =  urlConnection.getInputStream();

            byte[] b = new byte[1024];

            int len;

            StringBuilder sb = new StringBuilder();

            while ((len=is.read(b))!= -1){
                sb.append(new String(b,0,len));
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }

}
