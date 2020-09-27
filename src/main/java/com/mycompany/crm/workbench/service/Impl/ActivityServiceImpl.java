package com.mycompany.crm.workbench.service.Impl;

import com.mycompany.crm.settings.dao.UserDao;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.DateTimeUtil;
import com.mycompany.crm.utils.UUIDUtil;
import com.mycompany.crm.vo.PaginationVO;
import com.mycompany.crm.workbench.dao.ActivityDao;
import com.mycompany.crm.workbench.dao.ActivityRemarkDao;
import com.mycompany.crm.workbench.domain.Activity;
import com.mycompany.crm.workbench.domain.ActivityRemark;
import com.mycompany.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRemarkDao activityRemarkDao;
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

    public boolean delete(HttpServletRequest req, HttpServletResponse resp) {
        boolean flag = false;
        String[] ids = req.getParameterValues("id");
        //需要先删除与活动关联的所有活动记录，再删除活动
        int actMarkCount = activityRemarkDao.getCountByAids(ids);
        int deleteActMarkCount = activityRemarkDao.deleteByAids(ids);
        if(actMarkCount == deleteActMarkCount)
            flag = true;
        else
            flag = false;
        int deleteActivityCount = activityDao.delete(ids);
        if(deleteActivityCount == ids.length)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {
        List<User> uList = userDao.getUserList();
        Activity a = activityDao.getActivityById(id);
        Map<String,Object> res = new HashMap<String, Object>();
        res.put("uList",uList);
        res.put("a",a);
        return res;
    }

    public int update(Activity activity) {
        activity.setEditTime(DateTimeUtil.getSysTime());
        return activityDao.update(activity);
    }

    public Activity detail(String id) {
        Activity a = activityDao.getDetailById(id);
        return a;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> list = activityRemarkDao.getRemarkListByAid(activityId);
        return list;
    }

    public boolean deleteRemark(String remarkId) {
        int count = activityRemarkDao.deleteRemark(remarkId);
        return count==1? true:false;
    }

    public boolean saveRemark(ActivityRemark ar) {
        int res = activityRemarkDao.saveRemark(ar);
        if(res!=1){
            return false;
        }
        return true;
    }
}
