package com.mycompany.crm.workbench.dao;

import com.mycompany.crm.workbench.domain.Clue;

public interface ClueDao {

    int save(Clue c);

    Clue getDetailById(String id);

    int unbund(String relationId);
}
