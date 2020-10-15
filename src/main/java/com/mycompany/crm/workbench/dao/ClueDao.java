package com.mycompany.crm.workbench.dao;

import com.mycompany.crm.workbench.domain.Clue;
import com.mycompany.crm.workbench.domain.ClueActivityRelation;

public interface ClueDao {

    int save(Clue c);

    Clue getDetailById(String id);

    int unbund(String relationId);

    int bund(ClueActivityRelation car);
}
