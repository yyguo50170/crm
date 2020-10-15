package com.mycompany.crm.workbench.service.Impl;

import com.mycompany.crm.settings.dao.UserDao;
import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.UUIDUtil;
import com.mycompany.crm.workbench.dao.ClueDao;
import com.mycompany.crm.workbench.domain.Clue;
import com.mycompany.crm.workbench.domain.ClueActivityRelation;
import com.mycompany.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ClueDao clueDao;

    public List<User> getUserList(){
        return userDao.getUserList();
    }

    public boolean save(Clue c) {
        int res = clueDao.save(c);
        return res==1? true:false;
    }

    public Clue getDetailById(String id) {
        return clueDao.getDetailById(id);
    }

    public Boolean unbund(String relationId) {
        int res = clueDao.unbund(relationId);
        return res==1? true:false;
    }

    public boolean bund(String cid, String[] aids) {
        boolean flag = true;
        for(String aid:aids){
            ClueActivityRelation car = new ClueActivityRelation();
            car.setId(UUIDUtil.getUUID());
            car.setClueId(cid);
            car.setActivityId(aid);
            int res = clueDao.bund(car);
            flag= res !=1? false:true;
        }
        return flag;
    }
}
