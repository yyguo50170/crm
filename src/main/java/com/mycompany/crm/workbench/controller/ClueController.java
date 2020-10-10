package com.mycompany.crm.workbench.controller;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.PrintJson;
import com.mycompany.crm.workbench.service.ClueService;
import com.mycompany.crm.workbench.service.Impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("workbench/clue")
public class ClueController {

    @Autowired
    private ClueServiceImpl clueServiceImpl;
    @RequestMapping("/getUserList.do")
    @ResponseBody
    public String getUserList(){
        List<User> user = clueServiceImpl.getUserList();
        return PrintJson.getJsonString(user);
    }
}
