package com.mycompany.crm.listener;

import com.mycompany.crm.settings.domain.DicValue;
import com.mycompany.crm.settings.service.DicService;
import com.mycompany.crm.settings.service.impl.DicServiceImpl;
import com.mycompany.crm.utils.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    private DicService dicService;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        System.out.println("上下文作用域对象创建了");
        //DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());

        dicService = WebApplicationContextUtils
                .getWebApplicationContext(servletContextEvent.getServletContext())
                .getBean(DicServiceImpl.class);


        Map<String,List<DicValue>> dic = dicService.getAll();
        Set<String> set = dic.keySet();
        for(String key:set){
            List<DicValue> list = dic.get(key);
            application.setAttribute(key,list);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
