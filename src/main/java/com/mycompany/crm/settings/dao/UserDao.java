package com.mycompany.crm.settings.dao;

import com.mycompany.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public User login(@Param("loginAct") String loginAct,@Param("loginPwd") String loginPwd);

    List<User> getUserList();
}
