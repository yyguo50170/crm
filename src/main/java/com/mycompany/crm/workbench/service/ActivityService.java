package com.mycompany.crm.workbench.service;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.vo.PaginationVO;
import com.mycompany.crm.workbench.domain.Activity;
import com.mycompany.crm.workbench.domain.ActivityRemark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ActivityService {
    List<User> getUserList();
    int save(Activity activity);
    PaginationVO pageList(HttpServletRequest req, HttpServletResponse resp);
    boolean delete(HttpServletRequest req, HttpServletResponse resp);

    Map<String, Object> getUserListAndActivity(String id);

    int update(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    boolean deleteRemark(String remarkId);

    boolean saveRemark(ActivityRemark ar);

    boolean updateRemark(ActivityRemark ar);
    List<Activity>getActivityListByClueId(String clueId);
}
