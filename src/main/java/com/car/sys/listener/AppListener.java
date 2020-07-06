package com.car.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 12:15
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //取到ServletContext
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("ctx",context.getContextPath());
        System.err.println("------Servlet容器创建成功ctx被放到ServletContext作用域-------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
