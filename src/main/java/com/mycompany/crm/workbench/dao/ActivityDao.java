package com.mycompany.crm.workbench.dao;

import com.mycompany.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity activity);
    List<Activity> getActivityListByCondition(Map<String, Object> map);
    int getTotalByCondition(Map<String, Object> map);
    int delete(String[] ids);
    Activity getActivityById(String id);
    int update(Activity activity);
    Activity getDetailById(String id);
    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityListByNameAndNotByClueId(@Param("aname") String aname,@Param("clueId") String clueId);
}
