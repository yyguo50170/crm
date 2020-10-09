package com.mycompany.crm.settings.service.impl;

import com.mycompany.crm.settings.dao.DicTypeDao;
import com.mycompany.crm.settings.dao.DicValueDao;
import com.mycompany.crm.settings.domain.DicType;
import com.mycompany.crm.settings.domain.DicValue;
import com.mycompany.crm.settings.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {
    @Resource
    private DicTypeDao dicTypeDao;
    @Resource
    private DicValueDao dicValueDao;

    public Map<String, List<DicValue>> getAll() {
        List<DicType> dcTypes = dicTypeDao.getTypeList();
        Map<String, List<DicValue>> map = new HashMap<String, List<DicValue>>();

        for (DicType type:dcTypes) {
            String code = type.getCode();
            List<DicValue> dcValue = dicValueDao.getListByCode(code);
            map.put(type+"List",dcValue);
        }
        return map;
    }
}
