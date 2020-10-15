package com.mycompany.crm.workbench.service;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {
    List<User> getUserList();

    boolean save(Clue c);

    Clue getDetailById(String id);

    Boolean unbund(String relationId);

    boolean bund(String cid, String[] aids);
}
