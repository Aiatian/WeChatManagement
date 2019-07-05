package com.Aiatian.controller;

import com.Aiatian.services.impl.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WeChatController{

    @Autowired
    private WeChatService weChatService;


    /**
     * By: Aiatian
     * QQ:1924734429
     *
     * @Description //TODO
     * @Date
     * @Param  * @param null
     * @return
     **/
    @GetMapping()
    @ResponseBody
    public void weiXingConnect(PrintWriter out, @RequestParam("signature") String  signature,
                               @RequestParam("timestamp") String  timestamp,
                               @RequestParam("nonce") String  nonce,
                               @RequestParam("echostr") String  echostr){
        //http://aiatian.cross.echosite.cn/wcm/wx/WechatConnect?timestamp=111&signature=111&nonce=111&echostr=111
        if (WeChatService.check(timestamp,nonce,signature)){
            System.out.println("接入成功");
            out.write(echostr);
            out.flush();
            out.close();

        }else {
            System.out.println("接入失败");

        }

    }


    
    /**
     * By: Aiatian
     * QQ:1924734429
     * 
     * @Description //TODO
     * @Date  
     * @Param  * @param null
     * @return 
     **/
    @PostMapping()
    @ResponseBody
    public  void  messageHandling (HttpServletRequest req,HttpServletResponse resp){

        // resp.setCharacterEncoding("UTF-8"); 要在 PrintWriter 前面
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out =null;
        try {
            out = resp.getWriter();
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //处理消息 和 事件推送
        try {
            Map<String,String> requserMap = weChatService.parseRequest(req.getInputStream());
            //打印用户的输入 以及信息
            System.out.println(requserMap);

            String respXML=weChatService.getRespose(requserMap);

            //打印包装好的xml
            System.out.println(respXML);
            out.print(respXML);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
