package com.mycompany.crm.settings.dao;

import com.mycompany.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

public interface Userdao {
    public User login(@Param("loginAct") String loginAct,@Param("loginPwd") String loginPwd);
}
