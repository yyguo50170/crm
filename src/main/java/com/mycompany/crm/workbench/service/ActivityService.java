package com.mycompany.crm.workbench.service;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.vo.PaginationVO;
import com.mycompany.crm.workbench.domain.Activity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ActivityService {
    List<User> getUserList();
    int save(Activity activity);
    PaginationVO pageList(HttpServletRequest req, HttpServletResponse resp);
}
