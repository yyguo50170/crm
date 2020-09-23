package com.mycompany.crm.workbench.dao;
import com.mycompany.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int getCountByAids(String[] aids);
    int deleteByAids(String[] aids);
    List<ActivityRemark> getRemarkListByAid(String activityId);
    int deleteRemark(String remarkId);
}
