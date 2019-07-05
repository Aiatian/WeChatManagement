package com.Aiatian;


import com.Aiatian.mapper.IUserMapper;
import com.Aiatian.pojo.User;
import com.Aiatian.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TestCode {



    @Test
    public  void  testMain(){

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Mr'Tang");


        /*
        *   map中的item  是传进去的数据   item.toUpperCase()  是数据执行的操作
        *   forEach的item 指的是 for (String s : list) 的 s  遍历返回的接受变量
        * */
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(item->{
            System.out.println(item);
        });

    }


    @Test
    public  void  testCode(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.queryAllUser();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
