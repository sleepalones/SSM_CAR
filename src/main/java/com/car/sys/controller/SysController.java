package com.car.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 铭鸽鸽
 * @create 2020/6/26 13:19
 */
@Controller
@RequestMapping("/sys")
public class SysController {

    /**
     * 跳转菜单管理
     */
    @RequestMapping(value = "/toMenuManager.do")
    public String toMenuManager(){
        return "system/menu/menuManger";
    }

    @RequestMapping(value = "/toMenuLeft.do")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }

    @RequestMapping(value = "/toMenuRight.do")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }

    @RequestMapping("/toRoleManager.do")
    public String toRoleManager(){
        return "system/role/roleManager";
    }

    @RequestMapping("/toUserManager.do")
    public String toUserManager(){
        return "system/user/userManger";
    }

    /**
     * 跳转到日志管理页面
     */
    @RequestMapping("/toLogInfoManager.do")
    public String toLogInfoManager(){
        return "system/logInfo/logInfoManager";
    }

    /**
     * 跳转到公告管理页面
     */
    @RequestMapping("/toNewsManager.do")
    public String toNewsManager(){
        return "system/news/newsManager";
    }

}
