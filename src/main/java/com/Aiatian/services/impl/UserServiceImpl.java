package com.Aiatian.services.impl;

import com.Aiatian.dao.impl.UserDaoImpl;
import com.Aiatian.pojo.User;
import com.Aiatian.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDaoImpl userDao;


    @Override
    public List<User> queryAllUser() {
        List<User> users = userDao.queryAllUser();

        return users;
    }
}
