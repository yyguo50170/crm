package com.mycompany.crm.workbench.service.Impl;

import com.mycompany.crm.settings.dao.UserDao;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private UserDao userDao;

    public List<User> getUserList(){
        return userDao.getUserList();
    }
}
