package com.mycompany.crm.workbench.service.Impl;

import com.mycompany.crm.settings.dao.UserDao;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.DateTimeUtil;
import com.mycompany.crm.utils.UUIDUtil;
import com.mycompany.crm.vo.PaginationVO;
import com.mycompany.crm.workbench.dao.ActivityDao;
import com.mycompany.crm.workbench.domain.Activity;
import com.mycompany.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserDao userDao;

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public int save(Activity activity) {
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        return activityDao.save(activity);
    }

    public PaginationVO pageList(HttpServletRequest req, HttpServletResponse resp) {
        Map map = new HashMap();
        Integer pageNo=Integer.valueOf(req.getParameter("pageNo"));
        Integer pageSize=Integer.valueOf(req.getParameter("pageSize"));
        String name=req.getParameter("name");
        String owner=req.getParameter("owner");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");

        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(activityDao.getTotalByCondition(map));
        vo.setDataList(activityDao.getActivityListByCondition(map));
        return vo;
    }
}