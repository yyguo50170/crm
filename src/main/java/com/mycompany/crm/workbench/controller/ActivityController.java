package com.mycompany.crm.workbench.controller;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.PrintJson;
import com.mycompany.crm.vo.PaginationVO;
import com.mycompany.crm.workbench.domain.Activity;
import com.mycompany.crm.workbench.service.Impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("workbench/activity")
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public String getUserList(){
        List<User> list = activityService.getUserList();
        return PrintJson.getJsonString(list);
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public String save(Activity activity){
        int count = activityService.save(activity);
        Map map = new HashMap();
        if(count==1){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return PrintJson.getJsonString(map);
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public String pageList(HttpServletRequest req, HttpServletResponse resp){
        String res;
        PaginationVO vo = activityService.pageList(req,resp);
        res = PrintJson.getJsonString(vo);
        return res;
    }
    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(HttpServletRequest req, HttpServletResponse resp){
        boolean result = activityService.delete(req,resp);
        Map map = new HashMap();
        map.put("success",result);
        return PrintJson.getJsonString(map);
    }

    @RequestMapping("/getUserListAndActivity.do")
    @ResponseBody
    public String getUserListAndActivity(String id){
        Map<String,Object> map;
        map = activityService.getUserListAndActivity(id);
        return PrintJson.getJsonString(map);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public String update(Activity activity){
        int count = activityService.update(activity);
        Map map = new HashMap();
        if(count==1){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return PrintJson.getJsonString(map);
    }

    @RequestMapping("/detail.do")
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Activity a = activityService.detail(req.getParameter("id"));
        req.setAttribute("a",a);
        req.getRequestDispatcher("/workbench/activity/detail.jsp").forward(req,resp);
    }

}

