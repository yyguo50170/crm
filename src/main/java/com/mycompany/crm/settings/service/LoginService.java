package com.mycompany.crm.settings.service;

import com.mycompany.crm.exception.LoginException;
import com.mycompany.crm.settings.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    public User login(HttpServletRequest req, HttpServletResponse resp) throws LoginException;
}
