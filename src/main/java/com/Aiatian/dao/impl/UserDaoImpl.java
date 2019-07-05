package com.Aiatian.dao.impl;

import com.Aiatian.dao.IUserDao;
import com.Aiatian.mapper.IUserMapper;
import com.Aiatian.pojo.User;
import com.Aiatian.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Override
    public List<User> queryAllUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        List<User> users = mapper.queryAllUser();

        sqlSession.close();

        return users;
    }
}
