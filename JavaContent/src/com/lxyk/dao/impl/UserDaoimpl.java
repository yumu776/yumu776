package com.lxyk.dao.impl;

import com.alibaba.druid.util.JdbcUtils;
import com.lxyk.dao.UserDao;
import com.lxyk.pojo.User;
import com.lxyk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDaoimpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUser(String username, String password) {
        try {
            String spl = "selcet * from mydb where username = ? and password = ?";
            User user = template.queryForObject(spl, new BeanPropertyRowMapper<>(User.class), username, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
