package com.mycompany.crm.settings.service.impl;

import com.mycompany.crm.exception.LoginException;
import com.mycompany.crm.settings.dao.UserDao;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.settings.service.LoginService;
import com.mycompany.crm.utils.DateTimeUtil;
import com.mycompany.crm.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userdao;

    public User login(HttpServletRequest req, HttpServletResponse resp) throws LoginException {
        String loginAct = req.getParameter("loginAct");
        String loginPwd = req.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
        User user = userdao.login(loginAct,loginPwd);
        System.out.println(req.getRemoteAddr());
        if(user==null){
            throw new LoginException("用户名或密码错误");
        }else if (DateTimeUtil.getSysTime().compareTo(user.getExpireTime())>0){
            throw new LoginException("账户已经过了有效期");
        }else if("0".equals(user.getLockState())){
            throw new LoginException("账户被锁定中");
        }else if(user.getAllowIps()!=""&&!user.getAllowIps().contains(req.getRemoteAddr())){
            throw new LoginException("当前ip禁止登陆该账户");
        }
        return user;
    }
}

