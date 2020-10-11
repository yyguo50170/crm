package com.mycompany.crm.workbench.controller;

import com.mycompany.crm.settings.domain.User;
import com.mycompany.crm.utils.DateTimeUtil;
import com.mycompany.crm.utils.PrintJson;
import com.mycompany.crm.utils.UUIDUtil;
import com.mycompany.crm.workbench.domain.Clue;
import com.mycompany.crm.workbench.service.Impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/save.do")
    @ResponseBody
    public String save(Clue C, HttpServletRequest req){
        C.setId(UUIDUtil.getUUID());
        C.setCreateBy(((User)req.getSession().getAttribute("user")).getName());
        C.setCreateTime(DateTimeUtil.getSysTime());
        boolean flag = clueServiceImpl.save(C);
        Map map = new HashMap();
        map.put("success",flag);
        return PrintJson.getJsonString(map);
    }

    @RequestMapping("/detail.do")
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Clue c = clueServiceImpl.getDetailById(req.getParameter("id"));
        req.setAttribute("c",c);
        req.getRequestDispatcher("/workbench/clue/detail.jsp").forward(req,resp);
    }
}
