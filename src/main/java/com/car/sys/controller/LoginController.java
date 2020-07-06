package com.car.sys.controller;

import com.car.sys.constast.SysConstast;
import com.car.sys.domain.User;
import com.car.sys.service.LogInfoService;
import com.car.sys.service.UserService;
import com.car.sys.utils.WebUtils;
import com.car.sys.vo.LogInfoVo;
import com.car.sys.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 14:28
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private LogInfoService logInfoService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/toLogin.do")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 登录方法
     */
    @RequestMapping("/login.do")
    public String login(UserVo userVo, Model model){
        User user = userService.login(userVo);
        if (user != null) {
            //存放到session中
            WebUtils.getHttpSession().setAttribute("user",user);
            //记录登录日志，向sys_login_log里面插入数据
            LogInfoVo logInfoVo = new LogInfoVo();
            logInfoVo.setLogintime(new Date());
            logInfoVo.setLoginname(user.getRealname() + "-" + user.getLoginname());
            logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
            logInfoService.addLogInfo(logInfoVo);
            return "system/main/index";
        }else {
            model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
            return "system/main/login";
        }
    }

}
