package com.lxyk.service.impl;

import com.lxyk.dao.UserDao;
import com.lxyk.dao.impl.UserDaoimpl;
import com.lxyk.pojo.User;
import com.lxyk.service.UserService;

public class UserServiceimpl implements UserService {
    private UserDao userDao = new UserDaoimpl();
    @Override
    public User login(User user) {

        return userDao.findUser(user.getUsername(), user.getPassword());
    }
}
