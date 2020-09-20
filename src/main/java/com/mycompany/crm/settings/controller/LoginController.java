package com.mycompany.crm.settings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.crm.exception.LoginException;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.settings.service.impl.LoginServiceImpl;
import com.mycompany.crm.utils.PrintJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("settings/user")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping("/login.do")
    @ResponseBody
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入到了controller");
        String res = "";
        User user;
        Map map = new HashMap();
        ObjectMapper om = new ObjectMapper();
        try {
            user = loginServiceImpl.login(req, resp);
            map.put("success", true);
            req.getSession().setAttribute("user", user);
            res = PrintJson.getJsonString(map);
        } catch (LoginException e) {
            e.printStackTrace();
            map.put("success", false);
            if ("用户名或密码错误".equals(e.getMessage())) {
                map.put("msg", "用户名或密码错误");
                res = PrintJson.getJsonString(map);
            } else if ("账户已经过了有效期".equals(e.getMessage())) {
                map.put("msg", "账户已经过了有效期");
                res = PrintJson.getJsonString(map);
            } else if ("账户被锁定中".equals(e.getMessage())) {
                map.put("msg", "账户被锁定中");
                res = PrintJson.getJsonString(map);
            } else if ("当前ip禁止登陆该账户".equals(e.getMessage())) {
                map.put("msg", "当前ip禁止登陆该账户");
                res = PrintJson.getJsonString(map);
            }
        }
        return res;
    }
}
