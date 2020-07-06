package com.car.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 18:08
 */
@Controller
@RequestMapping("/desk")
public class DeskManager {

    @RequestMapping("/desk.do")
    public String toDeskManager(){
        return "system/main/deskManager";
    }

}
