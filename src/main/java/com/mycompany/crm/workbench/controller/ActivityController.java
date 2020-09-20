package com.mycompany.crm.workbench.controller;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.PrintJson;
import com.mycompany.crm.workbench.domain.Activity;
import com.mycompany.crm.workbench.service.Impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("workbench/activity")
public class ActivityController {
    //url: "workbench/activity/getUserList.do",
    //     /crm/workbench/activity/getUserList.do
    @Autowired
    private ActivityServiceImpl activityService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public String getUserList(){
        String listString;
        List<User> list = activityService.getUserList();
        listString = PrintJson.getJsonString(list);
        return listString;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public String save(Activity activity){
        String res;
        int count = activityService.save(activity);
        Map map = new HashMap();
        if(count==1){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        res = PrintJson.getJsonString(map);
        return res;
    }
}
