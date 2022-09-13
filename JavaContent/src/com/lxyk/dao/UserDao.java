package com.lxyk.dao;

import com.lxyk.pojo.User;

public interface UserDao {
    User findUser (String username,String password);
}
