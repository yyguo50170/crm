package com.mycompany.crm.settings.dao;

import com.mycompany.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}
