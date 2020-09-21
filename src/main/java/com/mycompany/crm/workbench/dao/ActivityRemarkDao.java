package com.mycompany.crm.workbench.dao;

public interface ActivityRemarkDao {
    int getCountByAids(String[] aids);
    int deleteByAids(String[] aids);
}
