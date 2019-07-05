package com.Aiatian.controller;

import com.Aiatian.pojo.User;
import com.Aiatian.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/queryUser")
    public  String queryAllUser(Model model){
        List<User> users = userService.queryAllUser();

        model.addAttribute("user",users);
        return "forward:/index2.jsp";
    }

    @RequestMapping("/queryUser2")
    @ResponseBody
    public  User queryAllUser2(){
            User user = new User();

            user.setId(1);
            user.setUsername("xiaoming");
            user.setAge(24);
            user.setCourse("语文");
        return user;
    }

    @RequestMapping("/queryUser3")
    @ResponseBody
    public  List<User> queryAllUser3(){
        List<User> users = userService.queryAllUser();
        return users;
    }
}
